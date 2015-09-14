package culun.app.blocksms.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ky on 9/14/2015.
 */
public class MySms {

    private long uid;

    private long id;

    private String body;

    private String fromnumber;

    private String tonumber;

    public MySms() {
    }

    public MySms(long id) {
        this.id = id;
        this.body = id + " body";
        this.fromnumber = "fromnumber " + id;
        this.tonumber = "tonumber " + id;
    }

    public MySms(long id, String body, String fromnumber, String tonumber) {
        this.id = id;
        this.body = body;
        this.fromnumber = fromnumber;
        this.tonumber = tonumber;
    }

    @Override
    public String toString() {
        return String.format("-uid: %s\n-id: %s\n-body: %s\n-fromNumber: %s\n-toNumber: %s\n", uid, id, body, fromnumber, tonumber);
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFromnumber() {
        return fromnumber;
    }

    public void setFromnumber(String fromnumber) {
        this.fromnumber = fromnumber;
    }

    public String getTonumber() {
        return tonumber;
    }

    public void setTonumber(String tonumber) {
        this.tonumber = tonumber;
    }
}
