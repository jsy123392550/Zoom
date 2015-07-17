package android.BB.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.BB.R;

/**
 * Created by KalinaRain on 2015/7/17.
 */
public class NearbyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ll_nearby, container, false);
    }
}
