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

import android.BB.R;

public class MainActivity extends AppCompatActivity implements OnClickListener{
	private static final String CURRENT_INDEX="current_index";
	private static final int NEARBY_INDEX=0;
	private static final int NEWS_INDEX=1;
	private static final int BB_INDEX=2;
	private static final int LINKMAN_INDEX=3;
	private static final int USER_INDEX=4;

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
	private Toolbar toolbar_center;
	private TextView tv_toolbar;
	private TextView tv_nearby;
	private TextView tv_news;
	private TextView tv_BB;
	private TextView tv_linkman;
	private TextView tv_user;
	private int curIndex;
	private Fragment curFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setDefaultContent();
		if(savedInstanceState == null) {
			curIndex=NEARBY_INDEX;
			nearbyFragment = NearbyFragment.newInstance();
			fragmentManager.beginTransaction().add(R.id.main_content, nearbyFragment,String.valueOf(NEARBY_INDEX)).commit();
			setCurStatus();
		}else{
			fragmentTransaction=fragmentManager.beginTransaction();
			curIndex =savedInstanceState.getInt(CURRENT_INDEX);
			nearbyFragment=fragmentManager.findFragmentByTag(String.valueOf(NEARBY_INDEX));
			newsFragment=fragmentManager.findFragmentByTag(String.valueOf(NEWS_INDEX));
			bbFragment=fragmentManager.findFragmentByTag(String.valueOf(BB_INDEX));
			linkmanFragment=fragmentManager.findFragmentByTag(String.valueOf(LINKMAN_INDEX));
			userinfoFragment=fragmentManager.findFragmentByTag(String.valueOf(USER_INDEX));
			setCurStatus();
			for(Fragment fragment:fragmentManager.getFragments()){
				if(fragment==curFragment)
					fragmentTransaction.show(fragment);
				else
					fragmentTransaction.hide(fragment);
			}
			fragmentTransaction.commit();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(CURRENT_INDEX, curIndex);
	}

	private void setDefaultContent() {
		toolbar_center.setTitle(MyConstants.TEXT_NULL);
		tv_toolbar.setText(MyConstants.MODULE_NEAR);
		setSupportActionBar(toolbar_center);
		layout_nearby.setOnClickListener(this);
		layout_BB.setOnClickListener(this);
		layout_linkman.setOnClickListener(this);
		layout_news.setOnClickListener(this);
		layout_user.setOnClickListener(this);
		fragmentManager = getSupportFragmentManager();
	}

	private void initView() {
		toolbar_center = (Toolbar) findViewById(R.id.main_toolbar);
		tv_toolbar= (TextView) toolbar_center.findViewById(R.id.toolbar_tv);
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
		reset();
		switch (v.getId()) {
			case R.id.main_bottom_nearby:
				if (nearbyFragment == null) {
					nearbyFragment = NearbyFragment.newInstance();
				}
				curIndex =NEARBY_INDEX;
				switchContent(curFragment, nearbyFragment);
				break;
			case R.id.main_bottom_news:
				if (newsFragment == null) {
					newsFragment = NewsFragment.newInstance();
				}
				curIndex =NEWS_INDEX;
				switchContent(curFragment,newsFragment);
				break;
			case R.id.main_bottom_BB:
				if (bbFragment == null) {
					bbFragment = BBFragment.newInstance();
				}
				curIndex =BB_INDEX;
				switchContent(curFragment,bbFragment);
				break;
			case R.id.main_bottom_linkman:
				if (linkmanFragment == null) {
					linkmanFragment = LinkmanFragment.newInstance();
				}
				curIndex =LINKMAN_INDEX;
				switchContent(curFragment,linkmanFragment);
				break;
			case R.id.main_bottom_userinfo:
				if (userinfoFragment == null) {
					userinfoFragment = UserInfoFragment.newInstance();
				}
				curIndex =USER_INDEX;
				switchContent(curFragment,userinfoFragment);
				break;
		}
		setCurStatus();
	}
	private void reset(){
		img_nearby.setImageResource(R.mipmap.main_bottom_nearby_normal);
		img_news.setImageResource(R.mipmap.main_bottom_news_normal);
		img_BB.setImageResource(R.mipmap.main_bottom_bb_normal);
		img_linkman.setImageResource(R.mipmap.main_bottom_linkman_normal);
		img_user.setImageResource(R.mipmap.main_bottom_userinfo_normal);
		tv_nearby.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_news.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_BB.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_linkman.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
		tv_user.setTextColor(getResources().getColor(R.color.main_bottom_module_text_normal));
	}

	private void switchContent(Fragment from,Fragment to){
		fragmentTransaction=fragmentManager.beginTransaction();
		if(curFragment!=to){
			if(to.isAdded()){
				fragmentTransaction.hide(from).show(to).commit();
			}else{
				fragmentTransaction.hide(from).add(R.id.main_content,to,String.valueOf(curIndex)).commit();
			}
		}
	}
	private void setCurStatus(){
		switch(curIndex){
			case NEARBY_INDEX:
				tv_toolbar.setText(MyConstants.MODULE_NEAR);
				tv_nearby.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
				img_nearby.setImageResource(R.mipmap.main_bottom_nearby_clicked);
				curFragment=nearbyFragment;
				break;
			case NEWS_INDEX:
				tv_toolbar.setText(MyConstants.MODULE_NEWS);
				tv_news.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
				img_news.setImageResource(R.mipmap.main_bottom_news_clicked);
				curFragment=newsFragment;
				break;
			case BB_INDEX:
				tv_toolbar.setText(MyConstants.APP_TAG);
				tv_BB.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
//				img_BB.setImageResource(R.mipmap.main_bottom_bb_press);
				curFragment=bbFragment;
				break;
			case LINKMAN_INDEX:
				tv_toolbar.setText(MyConstants.MODULE_LINKMAN);
				tv_linkman.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
				img_linkman.setImageResource(R.mipmap.main_bottom_linkman_clicked);
				curFragment=linkmanFragment;
				break;
			case USER_INDEX:
				tv_toolbar.setText(MyConstants.MODULE_ME);
				tv_user.setTextColor(getResources().getColor(R.color.main_bottom_module_text_press));
				img_user.setImageResource(R.mipmap.main_bottom_userinfo_clicked);
				curFragment=userinfoFragment;
				break;
		}
	}
}