package android.BB.ui.BB;

import android.BB.map.LocationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.BB.R;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class SetLocationActivity extends AppCompatActivity implements BDLocationListener{
    private MapView mapView;
    private Button btn_search;
    private EditText et_location;
    private BaiduMap baiduMap;
    private LocationHelper helper;
    private double latitude;
    private double longitude;
    private boolean isFirst=true;
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
        baiduMap=mapView.getMap();
        MapStatusUpdate msf=MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msf);
        baiduMap.setMyLocationEnabled(true);
        helper=new LocationHelper(this,this);
        helper.start();
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
}
