package android.BB.bean.nearby;

public class UserInfo {
    private int user_id;
    private String nickname;
    private String distance;
    private String user_head;
    private int credit;

    public UserInfo(String nickname, String distance, int user_id, String user_head, int credit) {
        this.nickname = nickname;
        this.distance = distance;
        this.user_id = user_id;
        this.user_head = user_head;
        this.credit = credit;
    }
    public UserInfo(){}
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
