package android.BB.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.BB.finals.MyConstants;
import android.BB.http.user.UserHttp;
import android.BB.http.user.UserHttp.LoginCallback;
import app.BB.R;
public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_username;
	private EditText et_pwd;
	private Button btn_login;
	private Button btn_regist;
	private UserHttp http;
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
		btn_login.setOnClickListener(this);
		btn_regist.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.login_btn_login:
			Log.d(MyConstants.APP_TAG, et_username.getText().toString()+"����"+et_pwd.getText().toString());
			http.login(et_username.getText().toString(), et_pwd.getText().toString(), new LoginCallback() {
				
				@Override
				public void onSuccess() {
					Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onPrepare() {
					Toast.makeText(getApplicationContext(), "prepare", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onFailure(String errorMsg) {
					Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
				}
			});
			break;
		case R.id.login_btn_regist:
			startActivity(new Intent(this, RegistActivity.class));
			break;
		}
	}
}
