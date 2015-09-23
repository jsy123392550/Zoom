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
	/**
	 * 拍照回调
	 */
	public static final int REQUESTCODE_UPLOADAVATAR_CAMERA = 1;//拍照修改头像
	public static final int REQUESTCODE_UPLOADAVATAR_LOCATION = 2;//本地相册修改头像
	public static final int REQUESTCODE_UPLOADAVATAR_CROP = 3;//系统裁剪头像

	public static final int REQUESTCODE_TAKE_CAMERA = 0x000001;//拍照
	public static final int REQUESTCODE_TAKE_LOCAL = 0x000002;//本地图片
	public static final int REQUESTCODE_TAKE_LOCATION = 0x000003;//位置
	// 上传头像的地址
	public static final String UPLOADIMAGE_PATH = "";
	// app检查更新的地址
	public static final String CHECK_UPDATE = "";
	//以下为UI中所用字段名
	public static final String TEXT_USER="用户";
	public static final String TEXT_NO_NET = "当前没有网络";
	public static final String TEXT_UNKNOWN_EXCEPTION="发生未知错误";
	public static final String TEXT_LOGIN_PWD_WRONG="用户名或密码错误";
	public static final String TEXT_NULL="";
	public static final String TEXT_BB_PRESSED="已经BB";
	public static final String TEXT_ADDED_FRIEND="等待回复";
    //服务器响应请求操作成功的状态码
	public static final int RESPONSE_CODE_SUCCESS=666;
    //服务器响应请求操作失败的状态码
	public static final int RESPONSE_CODE_FAILURE=667;
	//服务器响应请求操作未知错误的状态码
	public static final int RESPONSE_CODE_UNKNOWN = 668;
	//这是Bmob的ApplicationId,用于初始化操作
	public static final String applicationId = "2081f5caa5ae23a472682e23cf1e91b6";
	//以下为全局使用的字段名
	public static final String KEY_STATE="state";
	public static final String KEY_DATA="data";
	/*以下为模块名*/
	public static final String MODULE_NEAR="附近";
	public static final String MODULE_ME="我";
	public static final String MODULE_LINKMAN="联系人";
	public static final String MODULE_NEWS="消息";
	//以下分类使用的字段名
	/*User类的字段名*/
	public static final String KEY_USER_ID="bb_num";
	public static final String KEY_USER_USERNAME="username";
	public static final String KEY_USER_HEADPHOTO="head_photo";
	public static final String KEY_USER_PERSIGN="personal_sign";
	public static final String KEY_USER_LABEL="label";
	public static final String KEY_USER_LEVEL="credit_level";
	public static final String KEY_USER_MONEY="money";
	public static final String KEY_USER_SESSION="session_id";
	public static final String KEY_USER_LOCATION="location";
	public static final String KEY_USER_SEX="sex";
	/*Information类的字段名*/
	public static final String KEY_INFORMATION_ID="info_id";
	public static final String KEY_INFORMATION_CONTENT="content";
	public static final String KEY_INFORMATION_TITLE="title";
	public static final String KEY_INFORMATION_LOCATION="location";
	public static final String KEY_INFORMATION_TIME="time";
	public static final String KEY_INFORMATION_MONEY="money";
	public static final String KEY_INFORMATION_BB_ID="bb_num";
	//以下为方法名
	public static final String METHOD_LOGIN="login";
	/*以下为toolbar中的标题字段*/
	public static final String KEY_TOOLBAR_BBDETAIL="BB详细信息";
	public static final String KEY_TOOLBAR_USERINFODETAIL="个人信息";
	public static final String KEY_TOOLBAR_USER_DETAIL="详细信息";
	/*Activity中Bundle的字段名*/
	public static final String KEY_IMAGE_URLS="img_urls";
	public static final String KEY_IMAGE_DETAIL_POSITION="img_detail_pos";
	public static final String KEY_HOSTINFO="hostinfo";
	public static final String KEY_COMMENT_LIST="comment_list";
	public static final String KEY_BB_TYPE="bb_type";
	public static final String KEY_USER_DETAIL="user_detail";
	/*以下为dialog中用到的字段名*/
	public static final String KEY_DIALOG_FORWARD="转发：";
	public static final String KEY_DIALOG_ADD_FRIEND="请求内容：";
	//是否已经设置过BB号
	public static boolean HAS_CHANGE_BBNUMBER = false;

}
