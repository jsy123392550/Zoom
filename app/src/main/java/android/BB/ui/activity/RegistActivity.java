package android.BB.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import app.BB.R;

public class RegistActivity extends Activity implements OnClickListener {
    private Button btn_regist;
    private Button btn_getnum;
    private TextView t_validate;
    private TextView t_confirm;
    private EditText et_validate;
    private CheckBox check_regist;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        flag=false;
        btn_regist = (Button) findViewById(R.id.regist_regist_btn);
        btn_regist.setOnClickListener(this);
        btn_getnum = (Button) findViewById(R.id.regist_getnum_btn);
        btn_getnum.setOnClickListener(this);
        t_validate= (TextView) findViewById(R.id.regist_validate_t);
        t_confirm= (TextView) findViewById(R.id.regist_validate_confirm);
        t_confirm.setOnClickListener(this);
        et_validate= (EditText) findViewById(R.id.regist_validate_et);
        check_regist= (CheckBox) findViewById(R.id.regist_checkbox);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.regist_regist_btn:
//                if(check_regist.isSelected()&&flag){
                    /*checkbox是否选中以及验证码是否正确*/
                    /**/
                    startActivity(new Intent(RegistActivity.this, RegistInfoActivity.class));
//                }else{
//
//                }
                break;
            case R.id.regist_getnum_btn:
                /*获取验证码操作*/
                /**/
                btn_getnum.setVisibility(View.INVISIBLE);
                t_validate.setVisibility(View.VISIBLE);
                et_validate.setVisibility(View.VISIBLE);
                t_confirm.setVisibility(View.VISIBLE);
                break;
            case R.id.regist_validate_confirm:
                /*对验证码进行验证*/
                /**/
                btn_regist.setEnabled(true);
                flag=true;
                break;
        }
    }
}
