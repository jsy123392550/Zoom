package android.BB.ui.user;

import android.BB.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by KalinaRain on 2015/8/29.
 */
public class UserFeedback extends AppCompatActivity {

    private EditText ed_feedback;
    private Button submit;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        init();

    }

    private void init() {
        ed_feedback = (EditText) findViewById(R.id.ed_feedback);
        submit = (Button) findViewById(R.id.bt_submit);

    }
}
