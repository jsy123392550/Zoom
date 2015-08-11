package android.BB.ui.main;

import android.BB.finals.MyConstants;
import android.BB.ui.BB.BBFragment;
import android.BB.ui.linkman.LinkmanFragment;
import android.BB.ui.nearby.NearbyFragment;
import android.BB.ui.news.NewsFragment;
import android.BB.ui.user.UserInfoFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.BB.R;

public class MainActivity extends AppCompatActivity implements OnClickListener{

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction = null;
	private LinearLayout layout_nearby;
	private LinearLayout layout_news;
	private LinearLayout layout_BB;
	private LinearLayout layout_linkman;
	private LinearLayout layout_user;
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
	private TextView tv_nearby;
	private TextView tv_news;
	private TextView tv_BB;
	private TextView tv_linkman;
	private TextView tv_user;
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
		layout_nearby.setOnClickListener(this);
		layout_BB.setOnClickListener(this);
		layout_linkman.setOnClickListener(this);
		layout_news.setOnClickListener(this);
		layout_user.setOnClickListener(this);
		fragmentManager = getSupportFragmentManager();
		nearbyFragment = new NearbyFragment();
		fragmentManager.beginTransaction().replace(R.id.main_content, nearbyFragment).commit();

	}

	private void initView() {
		toolbar= (Toolbar) findViewById(R.id.main_toolbar);
		tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
		layout_nearby= (LinearLayout) findViewById(R.id.main_bottom_nearby);
		layout_news= (LinearLayout) findViewById(R.id.main_bottom_news);
		layout_BB= (LinearLayout) findViewById(R.id.main_bottom_BB);
		layout_linkman= (LinearLayout) findViewById(R.id.main_bottom_linkman);
		layout_user= (LinearLayout) findViewById(R.id.main_bottom_userinfo);
		img_nearby = (ImageView) findViewById(R.id.img_bottom_nearby);
		img_news = (ImageView) findViewById(R.id.img_bottom_news);
		img_BB = (ImageView) findViewById(R.id.img_bottom_BB);
		img_linkman = (ImageView) findViewById(R.id.img_bottom_linkman);
		img_user = (ImageView) findViewById(R.id.img_bottom_userinfo);
		tv_nearby= (TextView) findViewById(R.id.tv_bottom_nearby);
		tv_news= (TextView) findViewById(R.id.tv_bottom_news);
		tv_BB= (TextView) findViewById(R.id.tv_bottom_BB);
		tv_linkman= (TextView) findViewById(R.id.tv_bottom_linkman);
		tv_user= (TextView) findViewById(R.id.tv_bottom_userinfo);
	}

	@Override
	public void onClick(View v) {
		fragmentTransaction=fragmentManager.beginTransaction();
		reset();
		switch (v.getId()) {
			case R.id.main_bottom_nearby:
				if (nearbyFragment == null) {
					nearbyFragment = new NearbyFragment();
				}
				tv_toolbar.setText(MyConstants.MODULE_NEAR);
				tv_nearby.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_nearby.setImageResource(R.mipmap.main_bottom_nearby_press);
				fragmentTransaction.replace(R.id.main_content, nearbyFragment);
				break;
			case R.id.main_bottom_news:
				if (newsFragment == null) {
					newsFragment = new NewsFragment();
				}
				tv_toolbar.setText(MyConstants.MODULE_NEWS);
				tv_news.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_news.setImageResource(R.mipmap.main_bottom_news_press);
				fragmentTransaction.replace(R.id.main_content, newsFragment);
				break;
			case R.id.main_bottom_BB:
				if (bbFragment == null) {
					bbFragment = new BBFragment();
				}
				tv_toolbar.setText(MyConstants.APP_TAG);
				tv_BB.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_BB.setImageResource(R.mipmap.main_bottom_bb_press);
				fragmentTransaction.replace(R.id.main_content, bbFragment);
				break;
			case R.id.main_bottom_linkman:
				if (linkmanFragment == null) {
					linkmanFragment = new LinkmanFragment();
				}
				tv_toolbar.setText(MyConstants.MODULE_LINKMAN);
				tv_linkman.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_linkman.setImageResource(R.mipmap.main_bottom_linkman_press);
				fragmentTransaction.replace(R.id.main_content, linkmanFragment);
				break;
			case R.id.main_bottom_userinfo:
				if (userinfoFragment == null) {
					userinfoFragment = new UserInfoFragment();
				}
				tv_toolbar.setText(MyConstants.MODULE_ME);
				tv_user.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_user.setImageResource(R.mipmap.main_bottom_userinfo_press);
				fragmentTransaction.replace(R.id.main_content, userinfoFragment);
				break;
		}

		fragmentTransaction.commit();
	}
	private void reset(){
		img_nearby.setImageResource(R.mipmap.main_bottom_nearby_normal);
		img_news.setImageResource(R.mipmap.main_bottom_news_normal);
		img_BB.setImageResource(R.mipmap.main_bottom_bb_normal);
		img_linkman.setImageResource(R.mipmap.main_bottom_linkman_normal);
		img_user.setImageResource(R.mipmap.ic_launcher);
		tv_nearby.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_news.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_BB.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_linkman.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_user.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
	}
}
