package android.BB.ui.main;

import android.BB.bean.user.User;
import android.BB.util.CollectionUtils;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.config.BmobConfig;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * TODO 右滑退出当前activity
 */
public class BaseActivity extends AppCompatActivity {

    Toast mToast;
    ProgressDialog progressDialog;
    CustomApplication mApplication;
    BmobUserManager userManager;
    BmobChatManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

    /** 打Log
     * ShowLog
     * @return void
     * @throws
     */
    public void ShowLog(String msg){
        Log.d("BB", msg);
    }

    /** 用于登陆或者自动登陆情况下的用户资料及好友资料的检测更新
     * @Title: updateUserInfos
     * @Description: TODO
     * @param
     * @return void
     * @throws
     */
    public void updateUserInfos(){
        //更新地理位置信息
        updateUserLocation();
        //查询该用户的好友列表(这个好友列表是去除黑名单用户的哦),目前支持的查询好友个数为100，如需修改请在调用这个方法前设置BmobConfig.LIMIT_CONTACTS即可。
        //这里默认采取的是登陆成功之后即将好于列表存储到数据库中，并更新到当前内存中,
        userManager.queryCurrentContactList(new FindListener<BmobChatUser>() {

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                if(arg0== BmobConfig.CODE_COMMON_NONE){
                    ShowLog(arg1);
                }else{
                    ShowLog("查询好友列表失败："+arg1);
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
//			ShowLog("saveLatitude ="+saveLatitude+",saveLongtitude = "+saveLongtitude);
//			ShowLog("newLat ="+newLat+",newLong = "+newLong);
            if(!saveLatitude.equals(newLat)|| !saveLongtitude.equals(newLong)){//只有位置有变化就更新当前位置，达到实时更新的目的
                User u = (User) userManager.getCurrentUser(User.class);
                final User user = new User();
//                user.setLocation(CustomApplication.lastPoint);
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
}
