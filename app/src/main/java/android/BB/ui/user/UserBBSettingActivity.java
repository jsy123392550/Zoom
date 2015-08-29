package android.BB.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/25.
 */
public class UserBBSettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView checkforupdate;
    private TextView feedback;
    private TextView aboutus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_setting);
        init();
    }

    private void init() {
        checkforupdate = (TextView) findViewById(R.id.tv_check_for_update);
        feedback = (TextView) findViewById(R.id.tv_user_feedback);
        aboutus = (TextView) findViewById(R.id.tv_about_us);

        feedback.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
