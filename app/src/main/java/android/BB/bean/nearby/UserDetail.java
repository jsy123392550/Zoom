package android.BB.bean.nearby;

import java.io.Serializable;

public class UserDetail implements Serializable{
    private String bb_num;
    private String nickname;
    private String sex;
    private String area;
    private String signature;
    private float credit;
    private String user_head;

    public UserDetail(String bb_num, String nickname, String sex, String area, String signature, String user_head, float credit) {
        this.bb_num = bb_num;
        this.nickname = nickname;
        this.sex = sex;
        this.area = area;
        this.signature = signature;
        this.user_head = user_head;
        this.credit = credit;
    }
    public UserDetail(){}
    public String getBb_num() {
        return bb_num;
    }

    public void setBb_num(String bb_num) {
        this.bb_num = bb_num;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
}
