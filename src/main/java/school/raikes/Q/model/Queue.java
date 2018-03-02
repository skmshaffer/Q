package school.raikes.Q.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queueCode;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @NotNull
    private User owner;

    @OneToMany(mappedBy = "queue")
    private List<QueueItem> queueItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueueCode() {
        return queueCode;
    }

    public void setQueueCode(String queueCode) {
        this.queueCode = queueCode;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<QueueItem> getQueueItems() {
        return queueItems;
    }

    public void setQueueItems(List<QueueItem> queueItems) {
        this.queueItems = queueItems;
    }
}
