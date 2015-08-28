package android.BB.ui.user;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import android.BB.R;

public class UserInfoFragment extends Fragment implements View.OnClickListener{

    private ImageView user_portrait;//由缓存中获取
    private TextView tv_username;
    private TextView tv_account;
    private LinearLayout ll_user_info_setting;
    private LinearLayout ll_user_BB;
    private LinearLayout ll_user_BB_coin;
    private LinearLayout ll_user_setting;

    public static UserInfoFragment newInstance(){
        UserInfoFragment fragment=new UserInfoFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("userinfofragment", "oncreateview");
        View view = inflater.inflate(R.layout.ll_userinfo, container, false);
        user_portrait = (ImageView) view.findViewById(R.id.img_user_portrait);
        tv_username = (TextView) view.findViewById(R.id.tv_user_name);
        tv_account = (TextView) view.findViewById(R.id.tv_user_account);
        ll_user_info_setting = (LinearLayout) view.findViewById(R.id.ll_user_personal_infosetting);
        ll_user_BB = (LinearLayout) view.findViewById(R.id.ll_user_BB);
        ll_user_BB_coin = (LinearLayout) view.findViewById(R.id.ll_user_BB_coin);
        ll_user_setting = (LinearLayout) view.findViewById(R.id.ll_user_setting);
        ll_user_info_setting.setOnClickListener(this);
        ll_user_BB.setOnClickListener(this);
        ll_user_BB_coin.setOnClickListener(this);
        ll_user_setting.setOnClickListener(this);
        return view;


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.ll_user_personal_infosetting:
                intent = new Intent(getActivity(), UserInfoDetailActivity.class);
                break;
            case R.id.ll_user_BB:
                intent = new Intent(getActivity(), UserBBhistoryActivity.class);
                break;
            case R.id.ll_user_BB_coin:
                intent = new Intent(getActivity(), UserBBcoinActivity.class);
                break;
            case R.id.ll_user_setting:
                intent = new Intent(getActivity(), UserBBSettingActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
