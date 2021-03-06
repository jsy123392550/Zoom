package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.BB.http.HttpConnect;
import android.BB.ui.main.BaseActivity;
import android.BB.ui.main.MainActivity;
import android.BB.util.DialogFactory;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.BB.http.user.UserHttp;

import android.BB.R;
import android.widget.TextView;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText et_username;
	private EditText et_pwd;
	private Button btn_login,btn_regist;
	private TextView forgetPass;
	private UserHttp http;
	private Dialog waitDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		http=new UserHttp(this);
		et_username = (EditText) findViewById(R.id.login_username_et);
		et_pwd = (EditText) findViewById(R.id.login_pwd_et);
		btn_login = (Button) findViewById(R.id.login_btn_login);
		btn_regist = (Button) findViewById(R.id.login_btn_regist);
		forgetPass = (TextView) findViewById(R.id.login_text_forgetpwd);
		btn_login.setOnClickListener(this);
		btn_regist.setOnClickListener(this);
		forgetPass.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.login_btn_login:
//			http.login(et_username.getText().toString(), et_pwd.getText().toString(), new HttpConnect.Callback() {
//
//				@Override
//				public void onSuccess() {
//
//					Log.d(MyConstants.APP_TAG, "login success");
//				}
//
//				@Override
//				public void onPrepare() {
//					showProgressDialog("登陆中");
//				}
//
//				@Override
//				public void onFailure(String errorMsg) {
//					Log.d(MyConstants.APP_TAG,errorMsg);
//				}
//			});
			startAnimSkip(MainActivity.class);
			finish();
			break;
		case R.id.login_btn_regist:
			startAnimSkip(RegistActivity.class);
			break;
		case R.id.login_text_forgetpwd:
			startAnimSkip(RetrievePass.class);
			break;
		default:
			break;
		}
	}
}
