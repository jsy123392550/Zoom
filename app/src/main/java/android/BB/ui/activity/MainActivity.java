package android.BB.ui.activity;

import android.BB.finals.MyConstants;
import android.BB.ui.fragment.BBFragment;
import android.BB.ui.fragment.LinkmanFragment;
import android.BB.ui.fragment.NearbyFragment;
import android.BB.ui.fragment.NewsFragment;
import android.BB.ui.fragment.UserInfoFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import app.BB.R;

public class MainActivity extends AppCompatActivity implements OnClickListener{

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction = null;
	private ImageView img_nearby;
	private ImageView img_news;
	private ImageView img_BB;
	private ImageView img_linkman;
	private ImageView img_user;
	private Fragment nearbyFragment;
	private Fragment newsFragment;
	private Fragment bbFragment;
	private Fragment linkmanFragment;
	private Fragment userinfoFragment;
	private Toolbar toolbar;
	private TextView tv_toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setDefaultContent();
	}

	private void setDefaultContent() {
		toolbar.setTitle(MyConstants.TEXT_NULL);
		tv_toolbar.setText(MyConstants.MODULE_NEAR);
		setSupportActionBar(toolbar);
		img_nearby.setOnClickListener(this);
		img_BB.setOnClickListener(this);
		img_linkman.setOnClickListener(this);
		img_news.setOnClickListener(this);
		img_user.setOnClickListener(this);
		fragmentManager = MainActivity.this.getFragmentManager();
		nearbyFragment = new NearbyFragment();
		fragmentManager.beginTransaction().replace(R.id.main_content, nearbyFragment).commit();

	}

	private void initView() {
		toolbar= (Toolbar) findViewById(R.id.main_toolbar);
		tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
		img_nearby = (ImageView) findViewById(R.id.img_bottom_nearby);
		img_news = (ImageView) findViewById(R.id.img_bottom_news);
		img_BB = (ImageView) findViewById(R.id.img_bottom_BB);
		img_linkman = (ImageView) findViewById(R.id.img_bottom_linkman);
		img_user = (ImageView) findViewById(R.id.img_bottom_userinfo);
	}

	@Override
	public void onClick(View v) {
		fragmentTransaction=getFragmentManager().beginTransaction();
		switch (v.getId()) {
			case R.id.img_bottom_nearby:
//					img_nearby.setImageDrawable(getDrawable(R.mipmap.login_head_img));
				tv_toolbar.setText(MyConstants.MODULE_NEAR);
				if (nearbyFragment == null) {
					nearbyFragment = new NearbyFragment();
				}
				fragmentTransaction.replace(R.id.main_content, nearbyFragment);
				break;
			case R.id.img_bottom_news:
				tv_toolbar.setText(MyConstants.MODULE_NEWS);
				if (newsFragment == null) {
					newsFragment = new NewsFragment();
				}
				fragmentTransaction.replace(R.id.main_content, newsFragment);
				break;
			case R.id.img_bottom_BB:
				tv_toolbar.setText(MyConstants.APP_TAG);
				if (bbFragment == null) {
					bbFragment = new BBFragment();
				}
				fragmentTransaction.replace(R.id.main_content, bbFragment);
				break;
			case R.id.img_bottom_linkman:
				tv_toolbar.setText(MyConstants.MODULE_LINKMAN);
				if (linkmanFragment == null) {
					linkmanFragment = new LinkmanFragment();
				}
				fragmentTransaction.replace(R.id.main_content, linkmanFragment);
				break;
			case R.id.img_bottom_userinfo:
				tv_toolbar.setText(MyConstants.MODULE_ME);
				if (userinfoFragment == null) {
					userinfoFragment = new UserInfoFragment();
				}
				fragmentTransaction.replace(R.id.main_content, userinfoFragment);
				break;
		}

		fragmentTransaction.commit();
	}

}
