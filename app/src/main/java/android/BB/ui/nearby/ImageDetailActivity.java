package android.BB.ui.nearby;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import app.BB.R;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
    }
    class ImageFragmentAdpater extends FragmentStatePagerAdapter{
        public List<String> imgs;
        public ImageFragmentAdpater(FragmentManager fm,List<String> imgs) {
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
