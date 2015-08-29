package android.BB.ui.news;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;

public class NewsFragment extends Fragment {
    public static NewsFragment newInstance(){
        NewsFragment fragment=new NewsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ll_news, container, false);
    }
}
