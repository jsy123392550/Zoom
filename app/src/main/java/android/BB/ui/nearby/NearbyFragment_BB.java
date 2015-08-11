package android.BB.ui.nearby;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.BB.R;

public class NearbyFragment_BB extends Fragment {


    public NearbyFragment_BB() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearby_fragment__bb, container, false);
    }


}
