package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KalinaRain on 2015/8/27.
 */
public class UserInfoPersonalSignatureChanging extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private EditText edt_psignature;
    private String psignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_psignature_changing);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar_userinfo_detail_psignature);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        modify_info = (TextView) toolbar.findViewById(R.id.tv_toolbar_modify_info);
        save = (TextView) toolbar.findViewById(R.id.tv_toolbar_save);
        modify_info.setText(getResources().getText(R.string.tv_set_psignature));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_psignature = (EditText) findViewById(R.id.ed_psignature);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                psignature = edt_psignature.getText().toString();
                if (psignature == null||"".equals(psignature)) {
                    Toast.makeText(UserInfoPersonalSignatureChanging.this, "内容不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    //向服务器发送

                    //返回界面后昵称
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("psignature", psignature);

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
