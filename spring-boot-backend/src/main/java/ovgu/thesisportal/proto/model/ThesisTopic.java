package ovgu.thesisportal.proto.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ThesisTopic {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long thesis_id;

    private String title;

    private Date date;

    private int user_id;

    public long getThesis_id() {
        return thesis_id;
    }

    public void setThesis_id(long thesis_id) {
        this.thesis_id = thesis_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
