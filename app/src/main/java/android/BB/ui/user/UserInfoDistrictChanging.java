package android.BB.ui.user;

import android.BB.bean.user.City;
import android.BB.bean.user.County;
import android.BB.bean.user.Province;
import android.BB.db.UserDistrictDB;
import android.BB.finals.MyConstants;
import android.BB.util.DistrictUtil;
import android.BB.util.HttpUtil;
import android.BB.util.ToastUtils;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KalinaRain on 2015/8/27.
 * 用于修改用户地区
 * 目前只支持中国境内的各省市
 * TODO 定位功能
 */
public class UserInfoDistrictChanging extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private String district;
    private TextView location;
    private ListView listView;
    public static final int LEVEL_PROVINCE = 1;
    public static final int LEVEL_CITY = 2;
    public static final int LEVEL_COUNTY = 3;
    private UserDistrictDB userDistrictDB;
    private ProgressDialog progressDialog;
    private List<String> dataList = new ArrayList<String>();
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县列表
     */
    private List<County> countyList;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 选中的城市
     */
    private County selectedCounty;
    /**
     * 当前选中的级别
     */
    private int currentLevel;

    private boolean countyHasAdded = false;//county是否已经添加

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_district_changing);
        init();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_userinfo_detail_district);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        modify_info = (TextView) toolbar.findViewById(R.id.tv_toolbar_modify_info);
        save = (TextView) toolbar.findViewById(R.id.tv_toolbar_save);
        modify_info.setText(getResources().getText(R.string.tv_set_district));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                } else {
                    finish();
                }
            }
        });
        location = (TextView) findViewById(R.id.tv_user_now_location);
        listView = (ListView) findViewById(R.id.listview_district);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDistrict();
            }
        });
        adapter = new ArrayAdapter<String>(this, R.layout.item_district_name, dataList);
        listView.setAdapter(adapter);
        userDistrictDB = UserDistrictDB.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(index);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(index);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    if (!countyHasAdded) {
                        district += ("-" + countyList.get(index).getCountyName());
                        location.setText(district);
                    }
                    countyHasAdded = true;
                }
            }
        });
        queryProvinces();  // 加载省级数据


    }

    /**
     * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryProvinces() {
        provinceList = userDistrictDB.loadProvinces();
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            district = "中国";
            location.setText(district);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(null, "province");
        }
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCities() {
        cityList = userDistrictDB.loadCities(selectedProvince.getId());
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            district = selectedProvince.getProvinceName();
            location.setText(district);
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(selectedProvince.getProvinceCode(), "city");
        }
    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCounties() {
        countyList = userDistrictDB.loadCounties(selectedCity.getId());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            district += ("-" + selectedCity.getCityName());
            location.setText(district);
            currentLevel = LEVEL_COUNTY;
        } else {
            queryFromServer(selectedCity.getCityCode(), "county");
        }
    }

    /**
     * 根据传入的代号和类型从服务器上查询省市县数据。
     */
    private void queryFromServer(final String code, final String type) {
        String address;
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type)) {
                    result = DistrictUtil.handleProvincesResponse(userDistrictDB, response);
                } else if ("city".equals(type)) {
                    result = DistrictUtil.handleCitiesResponse(userDistrictDB, response, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = DistrictUtil.handleCountiesResponse(userDistrictDB, response, selectedCity.getId());
                }
                if (result) {
                    // 通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                // 通过runOnUiThread()方法回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        ToastUtils.showShort(UserInfoDistrictChanging.this, "加载失败");
                    }
                });
            }
        });
    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 捕获Back按键，根据当前的级别来判断，此时应该返回市列表、省列表、还是直接退出。
     */
    @Override
    public void onBackPressed() {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else {
            finish();
        }
    }


    private class DistrictAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_district_name, null);
            return convertView;
        }

    }

    /**
     * 返回并设置用户地区
     */
    private void setDistrict() {
        setResult(RESULT_OK, this.getIntent().putExtra("district", district));
        UserInfoDistrictChanging.this.finish();
    }
}
