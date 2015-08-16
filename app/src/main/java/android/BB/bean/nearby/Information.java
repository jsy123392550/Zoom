package android.BB.bean.nearby;

import android.BB.bean.BaseBean;
import android.BB.finals.MyConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Information extends BaseBean {
    private int info_id;
    private String title;
    private String content;
    private String location;
    private String time;
    private int money;
    private int bb_num;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBb_num() {
        return bb_num;
    }

    public void setBb_num(int bb_num) {
        this.bb_num = bb_num;
    }

    @Override
    public void parse(JSONObject object) {
        try{
            Iterator<String> it=object.keys();
            String key="";
            while(it.hasNext()){
                key=it.next();
                switch(key){
                    case MyConstants.KEY_INFORMATION_ID:
                        this.info_id=object.getInt(MyConstants.KEY_INFORMATION_ID);
                        break;
                    case MyConstants.KEY_INFORMATION_TITLE:
                        this.title=object.getString(MyConstants.KEY_INFORMATION_TITLE);
                        break;
                    case MyConstants.KEY_INFORMATION_CONTENT:
                        this.content=object.getString(MyConstants.KEY_INFORMATION_CONTENT);
                        break;
                    case MyConstants.KEY_INFORMATION_LOCATION:
                        this.location=object.getString(MyConstants.KEY_INFORMATION_LOCATION);
                        break;
                    case MyConstants.KEY_INFORMATION_TIME:
                        this.time=object.getString(MyConstants.KEY_INFORMATION_TIME);
                        break;
                    case MyConstants.KEY_INFORMATION_MONEY:
                        this.money=object.getInt(MyConstants.KEY_INFORMATION_MONEY);
                        break;
                    case MyConstants.KEY_INFORMATION_BB_ID:
                        this.bb_num=object.getInt(MyConstants.KEY_INFORMATION_BB_ID);
                        break;
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Information{" +
                "info_id=" + info_id +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                ", bb_num=" + bb_num +
                '}';
    }
}
