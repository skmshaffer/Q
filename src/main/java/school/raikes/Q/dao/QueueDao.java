package school.raikes.Q.dao;

import java.util.List;

import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.model.User;

public interface QueueDao {
    List<Queue> findAll();

    List<Queue> findByOwner(User owner);

    Queue findById(Long id);

    Queue findByQueueItem(QueueItem queueItem);

    Queue findByQueueCode(String queueCode);

    void save(Queue queue);

    void delete(Queue queue);
}
