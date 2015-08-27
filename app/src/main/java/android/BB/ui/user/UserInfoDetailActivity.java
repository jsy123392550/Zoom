package android.BB.ui.user;

import android.BB.bean.nearby.UserInfo;
import android.BB.finals.MyConstants;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.BB.R;

/**
 * Created by KalinaRain on 2015/8/25.
 */
public class UserInfoDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView tv_toolbar_center;
    private RelativeLayout rl_portrait;
    private RelativeLayout rl_nickname;
    private RelativeLayout rl_bbnumber;
    private RelativeLayout rl_gender;
    private RelativeLayout rl_district;
    private RelativeLayout rl_personality_signature;
    private RatingBar ratingBar_credit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_detailinfo);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.userinfo_detail_toolbar);
        tv_toolbar_center = (TextView) toolbar.findViewById(R.id.toolbar_tv);
        rl_portrait = (RelativeLayout) findViewById(R.id.rl_portrait);
        rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
        rl_bbnumber = (RelativeLayout) findViewById(R.id.rl_bbnumber);
        rl_gender = (RelativeLayout) findViewById(R.id.rl_gender);
        rl_district = (RelativeLayout) findViewById(R.id.rl_district);
        rl_personality_signature = (RelativeLayout) findViewById(R.id.rl_personality_signature);
        ratingBar_credit = (RatingBar) findViewById(R.id.ratingbar_credit);
        rl_portrait.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
        rl_bbnumber.setOnClickListener(this);
        rl_gender.setOnClickListener(this);
        rl_district.setOnClickListener(this);
        rl_personality_signature.setOnClickListener(this);

        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar_center.setText(MyConstants.KEY_TOOLBAR_USERINFODETAIL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
//        ratingBar_credit.setRating(new UserInfo().getCredit());
        ratingBar_credit.setRating(3.0f);//设置信用指数为3
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_nickname:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoNickNameChanging.class);
                break;
            case R.id.rl_bbnumber:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoBBNumberChanging.class);
                break;
            case R.id.rl_gender:
                /*开启一个对话框*/
                intent = new Intent(UserInfoDetailActivity.this, UserBBcoinActivity.class);
                break;
            case R.id.rl_district:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoDistrictChanging.class);
                break;
            case R.id.rl_personality_signature:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoPersonalSignatureChanging.class);
                break;
            default:
                intent = new Intent();
                break;
        }
        startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/
}
