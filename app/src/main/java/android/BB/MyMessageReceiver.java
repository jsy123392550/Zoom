package android.BB;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cn.bmob.im.inteface.EventListener;
import cn.bmob.im.util.BmobLog;

/**
 * Created by Kalina on 2015/9/20.
 */
public class MyMessageReceiver extends BroadcastReceiver {
    // 事件监听
    public static ArrayList<EventListener> ehList = new ArrayList<EventListener>();

    @Override
    public void onReceive(Context context, Intent intent) {
        String json = intent.getStringExtra("msg");
        BmobLog.i("收到的message = " + json);
        //省略其他代码
    }
}
