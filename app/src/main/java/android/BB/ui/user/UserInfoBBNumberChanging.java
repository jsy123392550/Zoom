package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
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

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private EditText edt_bbnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_bbnumber_changing);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_userinfo_detail_bbnumber);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        modify_info = (TextView) toolbar.findViewById(R.id.tv_toolbar_modify_info);
        save = (TextView) toolbar.findViewById(R.id.tv_toolbar_save);
        modify_info.setText(getResources().getText(R.string.tv_set_bbnumber));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_bbnumber = (EditText) findViewById(R.id.ed_bbnumber);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content_bb_number = edt_bbnumber.getText().toString();
                if (content_bb_number == null||"".equals(content_bb_number)) {
                    Toast.makeText(UserInfoBBNumberChanging.this, "内容不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    //确认符合规则
                    //向服务器发送，确认唯一

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
