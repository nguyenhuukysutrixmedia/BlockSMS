package culun.app.blocksms.model;

/**
 * Created by ky.nguyen on 14/09/2015.
 */
public class MyContact {

    private long uid;
    private long id;
    private String number;

    public MyContact() {
    }

    public MyContact(long id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("-uid: %s\n-id: %s\n-number: %s\n", uid, id, number);
    }

    @Override
    public boolean equals(Object o) {
        try {
            MyContact other = (MyContact) o;
            if (other.getNumber().equals(number)) {
                return true;
            }

        } catch (Exception e) {

        }
        return super.equals(o);
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
