package android.BB.ui.linkman;

import android.BB.R;
import android.BB.finals.MyConstants;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/9/2.
 */
public class LinkmanDetailInfo extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_detail_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman_detailinfo);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar_personal_info);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_detail_info = (TextView) toolbar.findViewById(R.id.toolbar_tv);
        tv_detail_info.setText("详细信息");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
