package android.BB.ui.main;

import android.BB.R;
import android.BB.finals.MyConstants;
import android.BB.ui.user.LoginActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import cn.bmob.im.BmobChat;

/**
 * 启动欢迎页面(引导页)——以后在做比较漂亮的引导页面
 * 判断是否自动登录
 */
public class SplashActivity extends BaseActivity {

    private static final int GO_HOME = 100;
    private static final int GO_LOGIN = 200;
    // 定位获取当前用户的地理位置
    private LocationClient mLocationClient;

    private BaiduReceiver mReceiver;// 注册广播接收器，用于监听网络以及验证key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //可设置调试模式，当为true的时候，会在logcat的BmobChat下输出一些日志，包括推送服务是否正常运行，如果服务端返回错误，也会一并打印出来。方便开发者调试
        BmobChat.DEBUG_MODE = true;
        //BmobIM SDK初始化--只需要这一段代码即可完成初始化
        BmobChat.getInstance(this).init(MyConstants.applicationId);
        //开启定位
        initLocClient();
        // 注册地图 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new BaiduReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userManager.getCurrentUser() != null) {
            // 每次自动登陆的时候就需要更新下当前位置和好友的资料，因为好友的头像，昵称啥的是经常变动的
            updateUserInfos();
            mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    startAnimSkip(MainActivity.class);
                    finish();
                    break;
                case GO_LOGIN:
                    startAnimSkip(LoginActivity.class);
                    finish();
                    break;
            }
        }
    };

    /**
     * 开启定位，更新当前用户的经纬度坐标
     * @Title: initLocClient
     * @Description: TODO 返回具体位置信息——如重庆
     * @param
     * @return void
     * @throws
     */
    private void initLocClient() {
        mLocationClient = CustomApplication.getInstance().mLocationClient;
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式:高精度模式
        option.setCoorType("bd09ll"); // 设置坐标类型:百度经纬度
        option.setScanSpan(1000);// 设置发起定位请求的间隔时间为1000ms:低于1000为手动定位一次，大于或等于1000则为定时定位
        option.setIsNeedAddress(true);// 需要包含地址信息
        //设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.setIgnoreKillProcess(false);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class BaiduReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                ShowToast("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                ShowToast("当前网络连接不稳定，请检查您的网络设置!");
            }
        }
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
