package android.BB;

import android.BB.finals.MyConstants;
import android.app.Application;

import org.kymjs.kjframe.bitmap.BitmapConfig;
import org.kymjs.kjframe.http.HttpConfig;

public class MyApp extends Application{
    private int mUserId;
    private String mUsername="";

    @Override
    public void onCreate() {
        super.onCreate();
        HttpConfig.CACHEPATH = MyConstants.CACHE_PATH;
        BitmapConfig.CACHEPATH=MyConstants.IMAGE_PATH;
    }

    public void saveLoginInfo(boolean flag){
        getSharedPreferences("login",MODE_PRIVATE).edit().putBoolean("logininfo",flag).commit();
    }
    public boolean getLoginInfo(){
        return getSharedPreferences("login",MODE_PRIVATE).getBoolean("logininfo",false);
    }
}
