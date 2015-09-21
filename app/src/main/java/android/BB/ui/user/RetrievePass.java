package android.BB.ui.user;

import android.BB.R;
import android.BB.ui.main.BaseActivity;
import android.os.Bundle;

/**
 * Created by Kalina on 2015/9/21.
 * 找回密码
 * 验证码过关后，重新设置密码，然后返回到登录界面重新登录
 */
public class RetrievePass extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pass);

    }
}
