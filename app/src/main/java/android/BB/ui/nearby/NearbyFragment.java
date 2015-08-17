package android.BB.ui.nearby;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class NearbyFragment extends Fragment {
    private static final String CURRENT_INDEX="current_index";
    private ViewPager viewpager;
    private NearbyBBFragment _BBFragment;
    private NearbyUserFragment _UserFragment;
    private List<Fragment> fragmentList;
    private FragAdapter adapter;
    private View indicator;
    private TextView tv_BB;
    private TextView tv_user;
    private int screenW;
    private int currIndex=0;
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
        _BBFragment=new NearbyBBFragment();
        _UserFragment=new NearbyUserFragment();
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
        DisplayMetrics dm=getActivity().getResources().getDisplayMetrics();
        screenW=dm.widthPixels;
        int width_indicator=screenW/2;
        indicator.setLayoutParams(new LinearLayout.LayoutParams(screenW / 2, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, dm)));
        indicator.setX(currIndex*width_indicator);
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
            if(positionOffset>0){
                indicator.setX(positionOffsetPixels/2);
            }
        }

        @Override
        public void onPageSelected(int position) {
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
