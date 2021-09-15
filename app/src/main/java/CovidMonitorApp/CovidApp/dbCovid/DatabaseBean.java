package CovidMonitorApp.CovidApp.dbCovid;

public class DatabaseBean {
    private int _id;
    private String state;
    private String content;

    public DatabaseBean() {
    }

    public DatabaseBean(int _id, String state, String content) {
        this._id = _id;
        this.state = state;
        this.content = content;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
