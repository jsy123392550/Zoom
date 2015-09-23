package android.BB.ui.user;

import android.BB.bean.User;
import android.BB.finals.MyConstants;
import android.BB.ui.main.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/25.
 */
public class UserBBSettingActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView tv_toolbar;
    private TextView checkforupdate;
    private TextView feedback;
    private TextView aboutus;
    private TextView logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_setting);
        init();
        initToolBarCenterTitle(toolbar,tv_toolbar,"设置");
    }

    private void init() {
        toolbar= (Toolbar) findViewById(R.id.toolbar_bbsetting);
        tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
        checkforupdate = (TextView) findViewById(R.id.tv_check_for_update);
        feedback = (TextView) findViewById(R.id.tv_user_feedback);
        aboutus = (TextView) findViewById(R.id.tv_about_us);
        logout = (TextView) findViewById(R.id.tv_logout);
        feedback.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_check_for_update:
                //检查更新
                break;
            case R.id.tv_user_feedback:
                Intent intent = new Intent(UserBBSettingActivity.this, UserFeedback.class);
                startActivity(intent);
                break;
            case R.id.tv_about_us:

                break;
            case R.id.tv_logout:
                //退出登录后，返回到登录页面
                User user = new User();
//                user.
                startAnimSkip(LoginActivity.class);
                finish();
                break;
            default:
                break;
        }
    }
}
