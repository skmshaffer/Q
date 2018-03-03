package school.raikes.Q.service;

import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

import java.util.List;

public interface QueueService {
    List<Queue> findAll();

    Queue findById(Long id);

    Queue findByQueueItem(QueueItem queueItem);

    void save(Queue queue);

    void delete(Queue queue);
}
