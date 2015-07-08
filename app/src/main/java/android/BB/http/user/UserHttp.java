package android.BB.http.user;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.SystemTool;

import android.BB.finals.MyConstants;
import android.BB.http.HttpConnect;

public class UserHttp extends HttpConnect{

	public UserHttp(Context context) {
		super(context);
	}
	public void login(String username,String pwd,final LoginCallback callback){
		HttpParams params=new HttpParams();
		params.put("username",username);
		params.put("pwd", pwd);
		setUseCache(false);
		if(!SystemTool.checkNet(mContext)){
			callback.onFailure(MyConstants.TEXT_NO_NET);
			return;
		}
		mHttp.post(MyConstants.SERVER_URL+"login", params, new HttpCallBack(){
			@Override
			public void onPreStart() {
				callback.onPrepare();
			}
			@Override
			public void onSuccess(String t) {
				callback.onSuccess();
				try {
					JSONObject jsonObj=new JSONObject(t);
					int state=jsonObj.getInt(MyConstants.KEY_STATE);
					if(state==MyConstants.RESPONSE_CODE_SUCCESS){
						//登录成功后的操作
						JSONObject ja=(JSONObject) jsonObj.get("data");
						String u=ja.getString("username");
						String p=ja.getString("pwd");
						Log.i(MyConstants.APP_TAG, u+"  "+p);
						callback.onSuccess();
					}
					if(state==MyConstants.RESPONSE_CODE_FAILURE){
						//用户名或密码错误的操作
						Log.i(MyConstants.APP_TAG, MyConstants.TEXT_LOGIN_PWD_WRONG);
						callback.onFailure(MyConstants.TEXT_LOGIN_PWD_WRONG);
					}
					if(state==MyConstants.RESPONSE_CODE_UNKNOWN){
						//服务器出现未知异常的操作
						callback.onFailure(MyConstants.TEXT_UNKNOWN_EXCEPTION);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					callback.onFailure(MyConstants.TEXT_UNKNOWN_EXCEPTION);
				}
			}
			@Override
			public void onFailure(int errorNo, String strMsg) {
				callback.onFailure(strMsg);
			}
		});
	}
	public interface LoginCallback{
		public void onPrepare();
		public void onSuccess();
		public void onFailure(String errorMsg);
	}
}
