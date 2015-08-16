package android.BB.bean.nearby;


public class BBInfo{
    private String user_head;
    private String title;
    private String distance;
    private String time;
    private String position;

    public BBInfo(String user_head, String title, String distance, String position, String time) {
        this.user_head = user_head;
        this.title = title;
        this.distance = distance;
        this.position = position;
        this.time = time;
    }
    public BBInfo(){}

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
