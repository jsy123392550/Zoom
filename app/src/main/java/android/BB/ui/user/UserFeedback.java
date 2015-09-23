package android.BB.ui.user;

import android.BB.R;
import android.BB.finals.MyConstants;
import android.BB.ui.main.BaseActivity;
import android.BB.util.ToastUtils;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/29.
 * TODO 联网提交反馈内容
 */
public class UserFeedback extends BaseActivity {

    private Toolbar toolbar;
    private TextView tv_text;
    private EditText ed_feedback;
    private Button submit;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        init();
        initToolBarCenterTitle(toolbar, tv_text, "反馈");
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_user_feedback);
        tv_text = (TextView) toolbar.findViewById(R.id.toolbar_tv);
//        toolbar.setTitle(MyConstants.TEXT_NULL);
//        tv_text.setText("反馈");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.back_arrow);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        ed_feedback = (EditText) findViewById(R.id.ed_feedback);
        submit = (Button) findViewById(R.id.bt_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = ed_feedback.getText().toString();
                if ("".equals(content) || content == null) {
                    ToastUtils.showShort(UserFeedback.this, "内容为空！");
                } else {
                    showProgressDialog("正在提交~~");
                    submitFedback(content);
                    ShowToast("提交成功！");

//                        finish();
                }
            }
        });
    }

    /**
     * 用Handler来更新UI
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {

                //关闭ProgressDialog

            }

        }
    };

    /**
     * 提交用户的反馈
     */
    private void submitFedback(String src) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //请求
                    Thread.sleep(2000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                closeProgressDialog();
                doToast();
            }
        }).start();

    }


    private void doToast() {
    }
}
