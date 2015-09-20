package android.BB.bean.user;

import android.BB.bean.BaseBean;
import android.BB.finals.MyConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.bmob.im.bean.BmobChatUser;

public class User extends BmobChatUser {
    private String bb_num;
    private String username;
    private String sortLetters;//显示名字拼音的首字母
    private String portrait;//头像
    private String personal_sign;//个性签名
    private String label;
    private int credit_level;//信用指数
    private int money;//
    private int session_id;
    private String location;//
    private boolean gender;//true为男，false为女

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBb_num() {
        return bb_num;
    }

    public void setBb_num(String bb_num) {
        this.bb_num = bb_num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPersonal_sign() {
        return personal_sign;
    }

    public void setPersonal_sign(String personal_sign) {
        this.personal_sign = personal_sign;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCredit_level() {
        return credit_level;
    }

    public void setCredit_level(int credit_level) {
        this.credit_level = credit_level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

//    @Override
//    public void parse(JSONObject object) {
//        try {
//            Iterator<String> it = object.keys();
//            String key = "";
//            while (it.hasNext()) {
//                key = it.next();
//                switch (key) {
//                    case MyConstants.KEY_USER_ID:
//                        this.bb_num = object.getInt(MyConstants.KEY_USER_ID);
//                        break;
//                    case MyConstants.KEY_USER_USERNAME:
//                        this.username = object.getString(MyConstants.KEY_USER_USERNAME);
//                        break;
//                    case MyConstants.KEY_USER_HEADPHOTO:
//                        this.head_photo = object.getString(MyConstants.KEY_USER_HEADPHOTO);
//                        break;
//                    case MyConstants.KEY_USER_PERSIGN:
//                        this.personal_sign = object.getString(MyConstants.KEY_USER_PERSIGN);
//                        break;
//                    case MyConstants.KEY_USER_LABEL:
//                        this.label = object.getString(MyConstants.KEY_USER_LABEL);
//                        break;
//                    case MyConstants.KEY_USER_LEVEL:
//                        this.credit_level = object.getInt(MyConstants.KEY_USER_LEVEL);
//                        break;
//                    case MyConstants.KEY_USER_MONEY:
//                        this.money = object.getInt(MyConstants.KEY_USER_MONEY);
//                        break;
//                    case MyConstants.KEY_USER_SESSION:
//                        this.session_id = object.getInt(MyConstants.KEY_USER_SESSION);
//                        break;
//                    case MyConstants.KEY_USER_LOCATION:
//                        this.location = object.getString(MyConstants.KEY_USER_LOCATION);
//                        break;
//                    case MyConstants.KEY_USER_SEX:
//                        this.sex = object.getString(MyConstants.KEY_USER_SEX).charAt(0);
//                        break;
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public String toString() {
        return "User{" +
                "bb_num=" + bb_num +
                ", username='" + username + '\'' +
                ", head_photo='" + portrait + '\'' +
                ", personal_sign='" + personal_sign + '\'' +
                ", label='" + label + '\'' +
                ", credit_level=" + credit_level +
                ", money=" + money +
                ", session_id=" + session_id +
                ", location='" + location + '\'' +
                ", gender=" + gender +
                '}';
    }
}
