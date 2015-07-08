package android.BB.finals;

public class MyConstants {
    //这个App的Log的TAG
	public static final String APP_TAG = "BB";
	public static final String WEB_SEPARATOR = "/";
    //主机地址
	public static final String HOST = "113.250.153.193:8088" + WEB_SEPARATOR
			+ "web_1" + WEB_SEPARATOR;
	public static final String HTTP = "http://";
    // 服务器地址
	public static final String SERVER_URL = HTTP + HOST;
    // app存放地址
	public static final String APP_PATH = WEB_SEPARATOR + "BB";
    // 缓存存放地址
	public static final String CACHE_PATH = APP_PATH + WEB_SEPARATOR + "cache";
    // 图片存放地址
	public static final String IMAGE_PATH = APP_PATH + WEB_SEPARATOR + "image";
	public static final String TEXT_NO_NET = "当前没有网络";
	public static final String TEXT_UNKNOWN_EXCEPTION="发生未知错误";
	public static final String TEXT_LOGIN_PWD_WRONG="用户名或密码错误";
    //服务器响应请求操作成功的状态码
	public static final int RESPONSE_CODE_SUCCESS=666;
    //服务器响应请求操作失败的状态码
	public static final int RESPONSE_CODE_FAILURE=667;
    //服务器响应请求操作未知错误的状态码
	public static final int RESPONSE_CODE_UNKNOWN=668;
    //以下为全局使用的字段名
	public static final String KEY_STATE="state";
	public static final String KEY_DATA="data";

}
