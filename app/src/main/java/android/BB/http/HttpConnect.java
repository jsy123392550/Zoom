package android.BB.http;

import android.content.Context;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpConfig;

import android.BB.finals.MyConstants;

public abstract class HttpConnect {
	protected Context mContext;
	protected KJHttp mHttp;
	protected HttpConfig mConfig;

	protected HttpConnect(Context context) {
		mContext = context;
		mConfig = new HttpConfig();
		mHttp = new KJHttp(mConfig);
	}

	protected void setUseCache(boolean isUseCache) {
		mConfig.cacheTime = isUseCache ? 60 * 24 : 0;
	}
	public interface Callback{
		public void onPrepare();
		public void onSuccess();
		public void onFailure(String errorMsg);
	}
}
