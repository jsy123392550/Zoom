package android.BB.ui.nearby;

import android.BB.bean.nearby.UserDetail;
import android.BB.finals.MyConstants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.BB.R;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.kymjs.kjframe.KJBitmap;

public class UserDetailActivity extends AppCompatActivity {
    private ImageView img_head;
    private ImageView img_sex;
    private TextView tv_bbnum;
    private TextView tv_nickname;
    private TextView tv_area;
    private TextView tv_signature;
    private TextView tv_toolbar;
    private RatingBar rb_credit;
    private Button btn_add;
    private UserDetail user;
    private KJBitmap kjBitmap;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        init();
    }
    private void init(){
        kjBitmap=new KJBitmap();
        toolbar= (Toolbar) findViewById(R.id.toolbar_user_detail);
        tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
        tv_toolbar.setText(MyConstants.KEY_TOOLBAR_USER_DETAIL);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        img_head= (ImageView) findViewById(R.id.img_user_detail_head);
        img_sex= (ImageView) findViewById(R.id.img_user_detail_sex);
        tv_bbnum= (TextView) findViewById(R.id.tv_user_detail_bbnum);
        tv_nickname= (TextView) findViewById(R.id.tv_user_detail_nickname);
        tv_area= (TextView) findViewById(R.id.tv_user_detail_area);
        tv_signature= (TextView) findViewById(R.id.tv_user_detail_signature);
        rb_credit= (RatingBar) findViewById(R.id.ratingbar_user_detail_credit);
        btn_add= (Button) findViewById(R.id.btn_user_detail_add);
        user= (UserDetail) getIntent().getSerializableExtra(MyConstants.KEY_USER_DETAIL);
        tv_bbnum.append(user.getBb_num());
        tv_nickname.append(user.getNickname());
        tv_area.setText(user.getArea());
        tv_signature.setText(user.getSignature());
        rb_credit.setRating(user.getCredit());
        kjBitmap.display(img_head,user.getUser_head());
        if(user.getSex().equals("男"))
            img_sex.setImageResource(R.mipmap.man);
        else
            img_sex.setImageResource(R.mipmap.woman);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbdetail, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
