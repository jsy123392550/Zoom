package android.BB.ui.main;

import android.BB.R;
import android.BB.bean.User;
import android.BB.finals.MyConstants;
import android.BB.ui.user.LoginActivity;
import android.BB.util.CollectionUtils;
import android.BB.view.DialogTips;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.config.BmobConfig;
import cn.bmob.v3.listener.FindListener;

/**
 * 基类
 * 用于SplashActivity、LoginActivity、RegisterActivity
 * TODO 右滑退出当前activity
 */
public class BaseActivity extends AppCompatActivity {

    Toast mToast;
    ProgressDialog progressDialog;
    CustomApplication mApplication;
    public BmobUserManager userManager;
    public BmobChatManager manager;
    onRightViewClickListener onRightViewClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = BmobUserManager.getInstance(this);
        manager = BmobChatManager.getInstance(this);

    }

    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void ShowToast(final int resId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (mToast == null) {
                    mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
                            Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                }
                mToast.show();
            }
        });
    }

    /**
     * 显示进度对话框
     * TODO 传入string
     */
    public void showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(content);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 只有左边的返回按钮和居中的标题
     */
    public void initToolBarCenterTitle(Toolbar toolbar, TextView tv_R_Id, String title) {
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_R_Id.setText(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);//设置返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 标题和右边的按钮（文字或图片）
     * 暂时不可用
     */
    public void initToolBarTitleAndRight(Toolbar toolbar, String title, ImageView right, onRightViewClickListener listener) {

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);//设置返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        TextView.setOnR
    }

    /**
     * TODO 添加动画
     * @param cla 即将进入的类
     */
    public void startAnimSkip(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }

    /**
     * TODO 添加动画
     * @param intent
     */
    public void startAnimSkip(Intent intent) {
        this.startActivity(intent);
    }

    /**
     * TODO 右滑退出当前Activity
     */
    public void finishAnimActivity() {
        this.finish();
    }

    /** 打Log
     * ShowLog
     * @return void
     * @throws
     */
    public void ShowLog(String msg){
        Log.d("BB", msg);
    }

    /** 显示下线的对话框
     * showOfflineDialog
     * @return void
     * @throws
     */
    public void showOfflineDialog(final Context context) {
        DialogTips dialog = new DialogTips(this,"您的账号已在其他设备上登录!", "重新登录");
        // 设置成功事件
        dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int userId) {
                CustomApplication.getInstance().logout();
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                dialogInterface.dismiss();
            }
        });
        // 显示确认对话框
        dialog.show();
        dialog = null;
    }

    /** 用于登陆或者自动登陆情况下的用户资料及好友资料的检测更新
     * @Title: updateUserInfos
     * @Description: TODO
     */
    public void updateUserInfos(){
        //更新地理位置信息
        updateUserLocation();
        //查询该用户的好友列表(这个好友列表是去除黑名单用户的哦),目前支持的查询好友个数为100，
        // 如需修改请在调用这个方法前设置BmobConfig.LIMIT_CONTACTS即可。
        //这里默认采取的是登陆成功之后即将好于列表存储到数据库中，并更新到当前内存中,
        userManager.queryCurrentContactList(new FindListener<BmobChatUser>() {

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                if (arg0 == BmobConfig.CODE_COMMON_NONE) {
                    ShowLog(arg1);
                } else {
                    ShowLog("查询好友列表失败：" + arg1);
                }
            }

            @Override
            public void onSuccess(List<BmobChatUser> arg0) {
                // TODO Auto-generated method stub
                // 保存到application中方便比较
                CustomApplication.getInstance().setContactList(CollectionUtils.list2map(arg0));
            }
        });
    }

    /** 更新用户的经纬度信息
     * @Title: uploadLocation
     * @Description: TODO
     * @param
     * @return void
     * @throws
     */
    public void updateUserLocation(){
        if(CustomApplication.lastPoint!=null){
            String saveLatitude  = mApplication.getLatitude();
            String saveLongtitude = mApplication.getLongtitude();
            String newLat = String.valueOf(CustomApplication.lastPoint.getLatitude());
            String newLong = String.valueOf(CustomApplication.lastPoint.getLongitude());
//			ShowLog("save纬度 = "+saveLatitude+",save经度 = "+saveLongtitude);
//			ShowLog("newLat ="+newLat+",newLong = "+newLong);
            if(!saveLatitude.equals(newLat)|| !saveLongtitude.equals(newLong)){//只有位置有变化就更新当前位置，达到实时更新的目的
                User u = (User) userManager.getCurrentUser(User.class);
                final User user = new User();
//                user.setLocation(CustomApplication.lastPoint);//由该类推导出实际位置
//                user.setObjectId(u.getObjectId());
//                user.update(this,new UpdateListener() {
//                    @Override
//                    public void onSuccess() {
//                        // TODO Auto-generated method stub
//                        CustomApplication.getInstance().setLatitude(String.valueOf(user.getLocation().getLatitude()));
//                        CustomApplication.getInstance().setLongtitude(String.valueOf(user.getLocation().getLongitude()));
////						ShowLog("经纬度更新成功");
//                    }
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        // TODO Auto-generated method stub
////						ShowLog("经纬度更新 失败:"+msg);
//                    }
//                });
            }else{
//				ShowLog("用户位置未发生过变化");
            }
        }
    }

    public void setOnRightViewClickListener(onRightViewClickListener listener) {
        this.onRightViewClickListener = listener;
    }

    public interface onRightViewClickListener {
        void onClick();
    }

    public interface onBackViewClickListener {
        void onClick();
    }
}
