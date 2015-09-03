package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/27.
 * 用于修改用户地区
 * 目前只支持中国境内的各省市
 * TODO 定位功能 地区列表
 */
public class UserInfoDistrictChanging extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private String district;
    private TextView location;
    private ListView listView;
    private DistrictAdapter adapter;
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
                finish();
            }
        });
        location = (TextView) findViewById(R.id.tv_user_now_location);
        listView = (ListView) findViewById(R.id.listview_district);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private class DistrictAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
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
            return null;
        }

    }

    private void setDistrict() {
        setResult(RESULT_OK, this.getIntent().putExtra("district", district));
        UserInfoDistrictChanging.this.finish();
    }
}
