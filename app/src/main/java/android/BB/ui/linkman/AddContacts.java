package android.BB.ui.linkman;

import android.BB.R;
import android.BB.finals.MyConstants;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by KalinaRain on 2015/9/3.
 * TODO 可能要做下面的显示
 */
public class AddContacts extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbar_text;
    private EditText edt_search;
    private String search_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman_addcontacts);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_add_contacts);
        toolbar_text = (TextView) toolbar.findViewById(R.id.tv_toolbar_info);
        toolbar_text.setText("添加联系人");
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_search = (EditText) findViewById(R.id.edt_linkman_search);
        search_content = edt_search.getText().toString();
    }
}
