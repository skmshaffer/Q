package school.raikes.Q.dao;

import java.util.List;

import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

public interface QueueDao {
    List<Queue> findAll();

    Queue findById(Long id);

    Queue findByQueueItem(QueueItem queueItem);

    void save(Queue queue);

    void delete(Queue queue);
}
