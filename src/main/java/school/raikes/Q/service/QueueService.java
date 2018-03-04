package school.raikes.Q.service;

import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.model.User;

import java.util.List;

public interface QueueService {
    List<Queue> findAll();

    List<Queue> findByOwner(User owner);

    Queue findById(Long id);

    Queue findByQueueItem(QueueItem queueItem);

    Queue findByQueueCode(String queueCode);

    void save(Queue queue);

    void delete(Queue queue);
}
