package android.BB.ui.BB;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BBFragment extends Fragment implements View.OnClickListener{
    private LinearLayout layout_location;
    private RelativeLayout layout_price;
    private TextView tv_location;
    private EditText et_title;
    private EditText et_content;
    private EditText et_price;
    public static BBFragment newInstance(){
        BBFragment fragment=new BBFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ll_bb, container, false);
        layout_location= (LinearLayout) view.findViewById(R.id.layout_bb_location);
        layout_price= (RelativeLayout) view.findViewById(R.id.layout_bb_price);
        tv_location= (TextView) view.findViewById(R.id.tv_bb_location);
        et_title= (EditText) view.findViewById(R.id.et_bb_title);
        et_content= (EditText) view.findViewById(R.id.et_bb_content);
        et_price= (EditText) view.findViewById(R.id.et_bb_price);
        init();
        return view;
    }
    private void init(){
        layout_location.setOnClickListener(this);
        layout_price.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.layout_bb_location:
                Intent intent=new Intent(getActivity(),SetLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_bb_price:
                break;
        }
    }
}
