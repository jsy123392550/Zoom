package android.BB.ui.nearby;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.BB.R;

public class NearbyFragment extends Fragment {
    private static final String CURRENT_INDEX="current_index";
    private static final int INDEX_RESOURCE=0;
    private static final int INDEX_NEED=1;
    private static final int INDEX_USER=2;
    private ViewPager viewpager;
    private NearbyNeedFragment bbFragment;
    private NearbyUserFragment userFragment;
    private NearbyResourceFragment resourceFragment;
    private List<Fragment> fragmentList;
    private FragAdapter adapter;
    private View indicator;
    private TextView tv_BB;
    private TextView tv_user;
    private TextView tv_resource;
    private int screenW;
    private int currIndex=INDEX_NEED;
    private int width_indicator;
    ClickListener clickListener;

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BB","nearbyfragment create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("BB", "nearbyfragment destroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("BB", "nearbyfragment pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("BB", "nearbyfragment stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("BB", "nearbyfragment destroyview");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("BB", "nearbyfragment attach");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("BB", "nearbyfragment activitycreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("BB", "nearbyfragment start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("BB", "nearbyfragment resume");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("BB", "nearbyfragment detach");
    }*/
    public static NearbyFragment newInstance() {
        NearbyFragment fragment = new NearbyFragment();
        return fragment;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX,currIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            currIndex=savedInstanceState.getInt(CURRENT_INDEX);
        }
        View view=inflater.inflate(R.layout.ll_nearby, container, false);
        fragmentList=new ArrayList<>();
        bbFragment = NearbyNeedFragment.newInstance();
        userFragment =NearbyUserFragment.newInstance();
        resourceFragment=NearbyResourceFragment.newInstance();
        fragmentList.add(resourceFragment);
        fragmentList.add(bbFragment);
        fragmentList.add(userFragment);
        tv_BB= (TextView) view.findViewById(R.id.tv_nearby_need);
        tv_user= (TextView) view.findViewById(R.id.tv_nearby_user);
        tv_resource= (TextView) view.findViewById(R.id.tv_nearby_resource);
        viewpager= (ViewPager) view.findViewById(R.id.viewpager_nearby);
        indicator=view.findViewById(R.id.view_nearby_indicator);
        init();
        return view;
    }
    private void init() {
        DisplayMetrics dm=getActivity().getResources().getDisplayMetrics();
        screenW=dm.widthPixels;
        width_indicator=screenW/3;
        indicator.setLayoutParams(new LinearLayout.LayoutParams(screenW / 3, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, dm)));
        indicator.setX(currIndex * width_indicator);
        adapter=new FragAdapter(getChildFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(INDEX_NEED);
        viewpager.addOnPageChangeListener(new PagerListener());
        clickListener=new ClickListener();
        tv_BB.setOnClickListener(clickListener);
        tv_user.setOnClickListener(clickListener);
        tv_resource.setOnClickListener(clickListener);
    }
    private class PagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            TranslateAnimation animation=new TranslateAnimation((currIndex-1)*width_indicator,(position-1)*width_indicator,0,0);
            animation.setDuration(300);
            animation.setFillAfter(true);
            indicator.startAnimation(animation);
            currIndex=position;
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
                case R.id.tv_nearby_need:
                    if(currIndex!=INDEX_NEED){
                        viewpager.setCurrentItem(INDEX_NEED);
                        currIndex=INDEX_NEED;
                    }
                    break;
                case R.id.tv_nearby_user:
                    if(currIndex!=INDEX_USER){
                        viewpager.setCurrentItem(INDEX_USER);
                        currIndex=INDEX_USER;
                    }
                    break;
                case R.id.tv_nearby_resource:
                    if(currIndex!=INDEX_RESOURCE){
                        viewpager.setCurrentItem(INDEX_RESOURCE);
                        currIndex=INDEX_RESOURCE;
                    }
            }
        }
    }
}
