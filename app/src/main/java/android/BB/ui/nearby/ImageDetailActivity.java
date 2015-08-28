package android.BB.ui.nearby;

import android.BB.finals.MyConstants;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import android.BB.R;

public class ImageDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private ImageFragmentAdpater adpater;
    private TextView tv_pageIndex;
    private StringBuilder str;
    private int curIndex;
    private int totalIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        init();
    }
    private void init(){
        ArrayList<String> imgs=getIntent().getStringArrayListExtra(MyConstants.KEY_IMAGE_URLS);
        curIndex=getIntent().getIntExtra(MyConstants.KEY_IMAGE_DETAIL_POSITION, 0);
        totalIndex= imgs.size();
        tv_pageIndex= (TextView) findViewById(R.id.tv_image_detail_pageIndex);
        viewPager= (ViewPager) findViewById(R.id.viewpager_image_detail);
        adpater=new ImageFragmentAdpater(getSupportFragmentManager(),imgs);
        viewPager.setAdapter(adpater);
        viewPager.setCurrentItem(curIndex);
        viewPager.addOnPageChangeListener(this);
        str=new StringBuilder((curIndex+1)+MyConstants.WEB_SEPARATOR+totalIndex);
        tv_pageIndex.setText(str);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        str.replace(0,1,String.valueOf(position+1));
        tv_pageIndex.setText(str);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ImageFragmentAdpater extends FragmentStatePagerAdapter{
        public ArrayList<String> imgs;
        public ImageFragmentAdpater(FragmentManager fm,ArrayList<String> imgs) {
            super(fm);
            this.imgs=imgs;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(imgs.get(position));
        }

        @Override
        public int getCount() {
            return imgs==null?0:imgs.size();
        }
    }
}
