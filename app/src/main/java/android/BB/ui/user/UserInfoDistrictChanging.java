package android.BB.ui.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/27.
 * 用于修改用户地区
 * 目前只支持中国境内的各省市
 */
public class UserInfoDistrictChanging extends AppCompatActivity {

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
        location = (TextView) findViewById(R.id.tv_user_now_location);
        listView = (ListView) findViewById(R.id.listview_district);
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
}
