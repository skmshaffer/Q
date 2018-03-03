package school.raikes.Q.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class QueueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    @NotNull
    private Queue queue;

    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User servicer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public User getServicer() {
        return servicer;
    }

    public void setServicer(User servicer) {
        this.servicer = servicer;
    }
}
