package android.BB.ui.linkman;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;

public class LinkmanFragment extends Fragment {
    public static LinkmanFragment newInstance(){
        LinkmanFragment fragment=new LinkmanFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ll_linkman, container, false);
    }
}
