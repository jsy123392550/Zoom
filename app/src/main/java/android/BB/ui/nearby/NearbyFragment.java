package android.BB.ui.nearby;

import android.BB.finals.MyConstants;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.kymjs.kjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class NearbyFragment extends Fragment {
    private ViewPager viewpager;
    private NearbyFragment_BB _BBFragment;
    private NearbyFragment_User _UserFragment;
    private List<Fragment> fragmentList;
    private FragAdapter adapter;
    private View indicator;
    private TextView tv_BB;
    private TextView tv_user;
    private int screenW;
    private int offset=0;
    private int currIndex=0;
    ClickListener clickListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ll_nearby, container, false);
        fragmentList=new ArrayList<>();
        _BBFragment=new NearbyFragment_BB();
        _UserFragment=new NearbyFragment_User();
        fragmentList.add(_BBFragment);
        fragmentList.add(_UserFragment);
        tv_BB= (TextView) view.findViewById(R.id.tv_nearby_BB);
        tv_user= (TextView) view.findViewById(R.id.tv_nearby_user);
        viewpager= (ViewPager) view.findViewById(R.id.viewpager_nearby);
        indicator=view.findViewById(R.id.view_nearby_indicator);
        init();
        return view;
    }
    private void init() {
        screenW=DensityUtils.getScreenW(getActivity());
        offset=screenW/2;
        indicator.setLayoutParams(new LinearLayout.LayoutParams(offset, DensityUtils.dip2px(getActivity(), 6)));
        adapter=new FragAdapter(getChildFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new PagerListener());
        clickListener=new ClickListener();
        tv_BB.setOnClickListener(clickListener);
        tv_user.setOnClickListener(clickListener);
    }
    private class PagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation=new TranslateAnimation(currIndex*offset,position*offset,0,0);
            animation.setDuration(300);
            animation.setFillAfter(true);
            currIndex=position;
            indicator.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch (id){
                case R.id.tv_nearby_BB:
                    if(currIndex==1){
                        viewpager.setCurrentItem(0);
                        currIndex=0;
                    }
                    break;
                case R.id.tv_nearby_user:
                    if(currIndex==0){
                        viewpager.setCurrentItem(1);
                        currIndex=1;
                    }
            }
        }
    }
}
