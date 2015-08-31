package android.BB.ui.BB;

import android.BB.map.LocationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.BB.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
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
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;


public class SetLocationActivity extends AppCompatActivity implements BDLocationListener,View.OnClickListener,OnGetPoiSearchResultListener{
    private MapView mapView;
    private Button btn_search;
    private EditText et_location;
    private BaiduMap baiduMap;
    private LocationHelper helper;
    private PoiSearch poiSearch;
    private double latitude;
    private double longitude;
    private boolean isFirst=true;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_set_location);
        init();

    }
    private void init(){
        mapView = (MapView) findViewById(R.id.map_bb_set_location);
        btn_search= (Button) findViewById(R.id.btn_bb_search);
        et_location= (EditText) findViewById(R.id.et_bb_set_location);
        btn_search.setOnClickListener(this);
        baiduMap=mapView.getMap();
        MapStatusUpdate msf=MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msf);
        baiduMap.setMyLocationEnabled(true);
        helper=new LocationHelper(this,this);
        helper.start();
        poiSearch=PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);

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
        helper.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        poiSearch.destroy();
        mapView=null;
    }
    private void setLocation(){
        LatLng ll = new LatLng(latitude,
                longitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.animateMapStatus(u);
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || mapView == null)
            return;
        latitude=bdLocation.getLatitude();
        longitude=bdLocation.getLongitude();
        city=bdLocation.getCity();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(100).latitude(latitude)
                .longitude(longitude).build();
        baiduMap.setMyLocationData(locData);
        if(isFirst){
            isFirst=false;
            setLocation();
        }
    }

    @Override
    public void onClick(View v) {
        poiSearch.searchInCity(new PoiCitySearchOption().city(city).keyword(et_location.toString()).pageNum(0));
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
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

    }
    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            poiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }
            return true;
        }
    }
}
