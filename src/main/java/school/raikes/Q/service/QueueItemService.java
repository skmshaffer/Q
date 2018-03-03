package school.raikes.Q.service;

import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

import java.util.List;

public interface QueueItemService {
    List<QueueItem> findAll();

    QueueItem findById(Long id);

    List<QueueItem> findByQueue(Queue queue);

    List<QueueItem> findByCompleted(boolean completed);

    List<QueueItem> findByCompleted(Queue queue, boolean completed);

    void save(QueueItem queueItem);

    void delete(QueueItem queueItem);
}
