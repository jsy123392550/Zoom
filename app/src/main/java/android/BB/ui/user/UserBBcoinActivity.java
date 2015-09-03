package android.BB.ui.user;

import android.BB.finals.MyConstants;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/8/25.
 */
public class UserBBcoinActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_bbcoin);
        init();
    }

    private void init() {
        toolbar= (Toolbar) findViewById(R.id.toolbar_bb_coin);
        tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar.setText("B币");
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
