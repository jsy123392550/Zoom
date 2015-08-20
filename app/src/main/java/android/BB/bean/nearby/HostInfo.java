package android.BB.bean.nearby;

public class HostInfo {
    private int user_id;
    private int praise;
    private int comment;
    private int forward;
    private float money;
    private String user_head;
    private String nickname;
    private String content;
    private String time;
    private String imgs[];

    public HostInfo(int user_id, String[] imgs, String time, String content, String user_head,String nickname, float money, int praise, int comment, int forward) {
        this.user_id = user_id;
        this.imgs = imgs;
        this.time = time;
        this.content = content;
        this.nickname=nickname;
        this.user_head = user_head;
        this.money = money;
        this.praise = praise;
        this.comment = comment;
        this.forward = forward;
    }
    public HostInfo(){}

    public int getUser_id() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }
}
