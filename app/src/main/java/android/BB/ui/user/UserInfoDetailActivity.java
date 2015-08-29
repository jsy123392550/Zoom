package android.BB.ui.user;

import android.BB.bean.nearby.UserInfo;
import android.BB.finals.MyConstants;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.BB.R;

/**
 * Created by KalinaRain on 2015/8/25.
 */
public class UserInfoDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView tv_toolbar_center;
    private RelativeLayout rl_nickname;
    private RelativeLayout rl_bbnumber;
    private RelativeLayout rl_gender;
    private RelativeLayout rl_district;
    private RelativeLayout rl_personality_signature;
    private RatingBar ratingBar_credit;
    private int gender = 1;//0代表女，1代表男，默认为1——男
    private ImageView img_man_confirm;
    private ImageView img_woman_confirm;
    private Dialog genderDialog;
    private TextView tv_nickname;
    private TextView tv_bbnumber;
    private TextView tv_gender;
    private TextView tv_district;
    private TextView tv_psignature;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_detailinfo);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.userinfo_detail_toolbar);
        tv_toolbar_center = (TextView) toolbar.findViewById(R.id.toolbar_tv);
        rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
        rl_bbnumber = (RelativeLayout) findViewById(R.id.rl_bbnumber);
        rl_gender = (RelativeLayout) findViewById(R.id.rl_gender);
        rl_district = (RelativeLayout) findViewById(R.id.rl_district);
        rl_personality_signature = (RelativeLayout) findViewById(R.id.rl_personality_signature);
        ratingBar_credit = (RatingBar) findViewById(R.id.ratingbar_credit);

        tv_nickname = (TextView) findViewById(R.id.tv_userInfo_detail_nickname);
        tv_bbnumber = (TextView) findViewById(R.id.tv_userInfo_detail_BBNumber);
        tv_gender = (TextView) findViewById(R.id.tv_userInfo_detail_gender);
        tv_district = (TextView) findViewById(R.id.tv_userInfo_detail_district);
        tv_psignature = (TextView) findViewById(R.id.tv_userInfo_detail_psignature);

        rl_nickname.setOnClickListener(this);
        rl_bbnumber.setOnClickListener(this);
        rl_gender.setOnClickListener(this);
        rl_district.setOnClickListener(this);
        rl_personality_signature.setOnClickListener(this);

        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar_center.setText(MyConstants.KEY_TOOLBAR_USERINFODETAIL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
//        ratingBar_credit.setRating(new UserInfo().getCredit());
        ratingBar_credit.setRating(3.0f);//设置信用指数为3
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_nickname:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoNickNameChanging.class);
                startActivity(intent);
                break;
            case R.id.rl_bbnumber:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoBBNumberChanging.class);
                startActivity(intent);
                break;
            case R.id.rl_gender:
                /*开启一个对话框*/
                startGenderChangeDialog();
                break;
            case R.id.rl_district:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoDistrictChanging.class);
                startActivity(intent);
                break;
            case R.id.rl_personality_signature:
                intent = new Intent(UserInfoDetailActivity.this, UserInfoPersonalSignatureChanging.class);
                startActivity(intent);
                break;
            default:
                Log.d("UserInfo", "未知跳转方向");
                break;
        }

    }

    /**
     * 自定义对话框
     */
    private void startGenderChangeDialog() {
        View dialog_gender = getLayoutInflater().inflate(R.layout.dialog_gender_chosen, (ViewGroup) findViewById(R.id.dialog_gender_chosen));
        img_man_confirm=((ImageView) dialog_gender.findViewById(R.id.img_confirm_man));
        img_woman_confirm=((ImageView) dialog_gender.findViewById(R.id.img_confirm_woman));
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(dialog_gender);
        genderDialog = builder.create();
        if (gender == 1) {
            img_woman_confirm.setVisibility(View.INVISIBLE);
            img_man_confirm.setVisibility(View.VISIBLE);
        } else if (gender == 0) {
            img_man_confirm.setVisibility(View.INVISIBLE);
            img_woman_confirm.setVisibility(View.VISIBLE);
        }
        genderDialog.show();
        RelativeLayout rl_man = (RelativeLayout) dialog_gender.findViewById(R.id.rl_changetowoman);
        RelativeLayout rl_woman = (RelativeLayout) dialog_gender.findViewById(R.id.rl_changetoman);
        rl_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_woman_confirm.setVisibility(View.INVISIBLE);
                img_man_confirm.setVisibility(View.VISIBLE);
                gender=1;
                genderTextChange();
            }
        });
        rl_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_man_confirm.setVisibility(View.INVISIBLE);
                img_woman_confirm.setVisibility(View.VISIBLE);
                gender=0;
                genderTextChange();
            }
        });


    }

    private void genderTextChange() {
        genderDialog.dismiss();
        if (gender == 1) {
            tv_gender.setText("男");
        } else {
            tv_gender.setText("女");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
