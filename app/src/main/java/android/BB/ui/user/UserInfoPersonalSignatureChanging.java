package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.BB.util.ToastUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KalinaRain on 2015/8/27.
 */
public class UserInfoPersonalSignatureChanging extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView modify_info;
    private TextView save;
    private EditText edt_psignature;
    private String psignature;
    private Intent intent;
//    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_psignature_changing);
        init();
    }

    private void init() {
        intent = getIntent();
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

        //保存按钮
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                psignature = edt_psignature.getText().toString();
                if (psignature == null || "".equals(psignature)) {
                    ToastUtils.showShort(UserInfoPersonalSignatureChanging.this, "内容不能为空！");
                } else {
                    //向服务器发送

                    //判断是否符合要求——长度
                    setSignature();

                }
            }
        });
    }

    //返回界面后设置个性签名
    private void setSignature() {
        setResult(RESULT_OK, intent.putExtra("psignature", psignature));
        UserInfoPersonalSignatureChanging.this.finish();
    }

}
