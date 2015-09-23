package android.BB.ui.user;

import android.BB.R;
import android.BB.ui.main.BaseActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Kalina on 2015/9/21.
 * 找回密码
 * 验证码过关后，重新设置密码，然后返回到登录界面重新登录
 */
public class RetrievePass extends BaseActivity {

    private Toolbar toolbar;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pass);

        init();
        initToolBarCenterTitle(toolbar, tv_text, "找回密码");
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_retrieve_pwd);
        tv_text = (TextView) toolbar.findViewById(R.id.toolbar_tv);

    }
}
