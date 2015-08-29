package android.BB.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KalinaRain on 2015/8/27.
 */
public class UserInfoNickNameChanging extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_back;
    private EditText edt_nickname;
    private TextView save;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_nickname_changing);
        init();
    }

    private void init() {
        img_back = (ImageView) findViewById(R.id.img_back_setnickname);
        edt_nickname = (EditText) findViewById(R.id.ed_nickname);
        save = (TextView) findViewById(R.id.tv_save_nickname);
        img_back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_setnickname:
                //返回
                finish();
                break;
            case R.id.tv_save_nickname:
                nickname = edt_nickname.getText().toString();
                if (nickname == null||"".equals(nickname)) {
                    Toast.makeText(UserInfoNickNameChanging.this, "内容不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    //向服务器发送

                    //返回界面后昵称
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("nickname", nickname);

                }
                break;
        }
    }
}
