package android.BB.ui.user;

import android.BB.ui.main.BaseActivity;
import android.BB.util.CountDownButtonHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.BB.R;

/**
 * 注册类
 */
public class RegistActivity extends BaseActivity implements OnClickListener {
    private Button btn_regist;
    private Button btn_getnum;
    private TextView t_validate;
    private Button btn_count_down;
    private EditText et_validate;
    private CheckBox check_regist;
    private boolean flag;
    private CountDownButtonHelper countDownButtonHelper;

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
        btn_count_down= (Button) findViewById(R.id.regist_count_down);
        countDownButtonHelper = new CountDownButtonHelper(btn_count_down, "已发送","发送验证码", 45, 1);//设置倒计时时间为45秒，间隔为1秒
        btn_count_down.setOnClickListener(this);
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
                /*获取验证码操作——获取验证码后，改变改行布局*/
                /*开始倒计时*/
                btn_getnum.setVisibility(View.INVISIBLE);
                t_validate.setVisibility(View.VISIBLE);
                et_validate.setVisibility(View.VISIBLE);
                btn_count_down.setVisibility(View.VISIBLE);
                countDownButtonHelper.start();
                break;
            case R.id.regist_count_down:
                /*重新发送验证码*/
                btn_regist.setEnabled(true);
                ShowToast("验证码已发送，请注意查收~~");
                countDownButtonHelper.start();
                flag=true;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        //销毁该activity，使验证码无效
        super.onDestroy();
    }
}
