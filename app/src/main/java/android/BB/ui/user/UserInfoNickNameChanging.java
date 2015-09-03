package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.BB.util.ToastUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/27.
 */
public class UserInfoNickNameChanging extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private EditText edt_nickname;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_nickname_changing);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_userinfo_detail_nickname);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        modify_info = (TextView) toolbar.findViewById(R.id.tv_toolbar_modify_info);
        save = (TextView) toolbar.findViewById(R.id.tv_toolbar_save);
        modify_info.setText(getResources().getText(R.string.tv_set_nickname));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_nickname = (EditText) findViewById(R.id.ed_nickname);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = edt_nickname.getText().toString();
                if (nickname == null || "".equals(nickname)) {
                    ToastUtils.showShort(UserInfoNickNameChanging.this, "内容不能为空！");
                } else {
                    if (nickname.length() > 12) {
                        ToastUtils.showShort(UserInfoNickNameChanging.this, "昵称长度不得超过12");
                        return;
                    }
                    //向服务器发送

                    //返回界面后昵称
                    setNickName();

                }
            }
        });
    }

    private void setNickName() {
        setResult(RESULT_OK, this.getIntent().putExtra("nickname", nickname));
        UserInfoNickNameChanging.this.finish();
    }
}
