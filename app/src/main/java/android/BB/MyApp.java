package android.BB;

import android.app.Application;

public class MyApp extends Application{
	private boolean mIsLogin=false;
    private int mUserId=0;
    private String mUsername="";
    public void saveLoginInfo(){
        mIsLogin=true;
    }
}
