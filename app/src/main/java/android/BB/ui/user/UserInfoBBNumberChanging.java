package android.BB.ui.user;

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
 * BB号只能够改一次
 * BB号唯一，所以必须访问网络确保唯一
 */
public class UserInfoBBNumberChanging extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_back;
    private EditText edt_bbnumber;
    private TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_bbnumber_changing);
        init();
    }

    private void init() {
        img_back = (ImageView) findViewById(R.id.img_back_setbbnumber);
        edt_bbnumber = (EditText) findViewById(R.id.ed_bbnumber);
        save = (TextView) findViewById(R.id.tv_save_bbnumber);
        img_back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_setbbnumber:
                //返回
                finish();
                break;
            case R.id.tv_save_bbnumber:
                String content_bb_number = edt_bbnumber.getText().toString();
                if (content_bb_number == null) {
                    Toast.makeText(UserInfoBBNumberChanging.this, "内容不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    //确认符合规则
                    //向服务器发送，确认唯一

                }
                break;
        }
    }
}
