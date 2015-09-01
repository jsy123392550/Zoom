package android.BB.ui.BB;

import android.BB.util.DialogFactory;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.BB.R;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.*;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.*;
import com.baidu.mapapi.search.sug.*;

import java.util.ArrayList;


public class SetLocationActivity extends AppCompatActivity implements BDLocationListener,View.OnClickListener,OnGetSuggestionResultListener{
    private MapView mapView;
    private Button btn_search;
    private AutoCompleteTextView et_location;
    private BaiduMap baiduMap;
    private LocationClientOption option;
    private LocationClient client;
    private PoiSearch poiSearch;
    private SuggestionSearch suggestionSearch;
    private ArrayAdapter<String> adapter;
    private boolean isFirst=true;
    private ArrayList<String> list;
    private String city;
    private Dialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_set_location);
        init();
        initMap();
    }
    private void initMap(){
        option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        client=new LocationClient(this,option);
        client.registerLocationListener(this);
        client.start();
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        poiSearch=PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(new GetPoiListener());
        suggestionSearch=SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(this);
    }
    private void init(){
        list=new ArrayList<>();
        mapView = (MapView) findViewById(R.id.map_bb_set_location);
        btn_search= (Button) findViewById(R.id.btn_bb_search);
        et_location= (AutoCompleteTextView) findViewById(R.id.et_bb_set_location);
        et_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("BB", "text change");
                if (s.length() <= 0) {
                    return;
                }
                suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(city).keyword(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adapter=new ArrayAdapter<String>(this,R.layout.tv_bb_set_location,list);
        et_location.setThreshold(1);
        et_location.setAdapter(adapter);
        btn_search.setOnClickListener(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        client.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        poiSearch.destroy();
        mapView=null;
    }
    private void setLocation(double latitude,double longitude,float zoom){
        LatLng ll = new LatLng(latitude,
                longitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.setMapStatus(u);
        if(zoom!=0){
            MapStatusUpdate statusUpdate=MapStatusUpdateFactory.zoomTo(zoom);
            baiduMap.animateMapStatus(statusUpdate);
        }
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || mapView == null)
            return;
        city=bdLocation.getCity();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        baiduMap.setMyLocationData(locData);
        if(isFirst){
            isFirst=false;
            setLocation(bdLocation.getLatitude(), bdLocation.getLongitude(),0);
        }
    }

    @Override
    public void onClick(View v) {
        poiSearch.searchInCity((new PoiCitySearchOption()).city(city).keyword(et_location.getText().toString()).pageNum(1));
        waitDialog=DialogFactory.createWaitDialog(this,"正在搜索…");
        waitDialog.show();
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }
        list.clear();
        for(SuggestionResult.SuggestionInfo info:suggestionResult.getAllSuggestions()){
            if(info.key!=null) {
                StringBuilder sb=new StringBuilder("(").append(info.city).append(")").append(info.key);
                list.add(sb.toString());
            }
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        et_location.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        et_location.showDropDown();
    }
    private class GetPoiListener implements OnGetPoiSearchResultListener{

        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if(waitDialog!=null)
                waitDialog.dismiss();
            if (poiResult == null
                    || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(SetLocationActivity.this, "未找到结果", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                baiduMap.clear();
                PoiOverlay overlay = new MyPoiOverlay(baiduMap);
                baiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(poiResult);
                overlay.addToMap();
                overlay.zoomToSpan();
                return;
            }
            if (poiResult.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

                // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
                String strInfo = "在";
                for (CityInfo cityInfo : poiResult.getSuggestCityList()) {
                    strInfo += cityInfo.city;
                    strInfo += ",";
                }
                strInfo += "找到结果";
                Toast.makeText(SetLocationActivity.this, strInfo, Toast.LENGTH_LONG)
                        .show();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.e("BB","poidetail");
        }
    }
    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            setLocation(poi.location.latitude, poi.location.longitude,17.0f);
            return true;
        }
    }
}
