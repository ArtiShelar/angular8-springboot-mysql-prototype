package ovgu.thesisportal.proto.model;
import javax.persistence.*;
import java.util.Date;

@Entity
public class ThesisTopic {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long thesis_id;

    private String topic_title;

    @Column(name = "date", columnDefinition="DATETIME")
    private Date date;

    @Column(name = "start_date", columnDefinition="DATE")
    private Date start_date;

    private String topic_desc;

    private String must_have;

    private String nice_to_have;

    private String supervisor;

    private String contact_email;

    public long getThesis_id() {
        return thesis_id;
    }

    public void setThesis_id(long thesis_id) {
        this.thesis_id = thesis_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getTopic_desc() {
        return topic_desc;
    }

    public void setTopic_desc(String topic_desc) {
        this.topic_desc = topic_desc;
    }

    public String getMust_have() {
        return must_have;
    }

    public void setMust_have(String must_have) {
        this.must_have = must_have;
    }

    public String getNice_to_have() {
        return nice_to_have;
    }

    public void setNice_to_have(String nice_to_have) {
        this.nice_to_have = nice_to_have;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }
}
