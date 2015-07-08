package android.BB.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import app.BB.R;

public class RegistActivity extends Activity implements OnClickListener{
	private Button btn_regist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initView();
	}
	private void initView(){
		btn_regist=(Button) findViewById(R.id.regist_regist_btn);
		btn_regist.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch(id){
		case R.id.regist_regist_btn:
			startActivity(new Intent(RegistActivity.this, RegistInfoActivity.class));
			break;
		}
	}
}
