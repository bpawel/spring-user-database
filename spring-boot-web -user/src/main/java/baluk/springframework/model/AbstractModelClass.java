package baluk.springframework.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */

@MappedSuperclass
public class AbstractModelClass implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private Date dateCreated;
    private Date lastUpdated;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }
}
