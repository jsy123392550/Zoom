package android.BB.bean.nearby;

/**
 * Created by Administrator on 2015/8/19 0019.
 */
public class Comment {
    private int user_id;
    private String nickname;
    private String user_head;
    private String time;
    private String content;

    public Comment(int user_id, String nickname, String user_head, String time, String content) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.user_head = user_head;
        this.time = time;
        this.content = content;
    }
    public Comment(){}
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
