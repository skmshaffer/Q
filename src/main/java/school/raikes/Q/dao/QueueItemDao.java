package school.raikes.Q.dao;

import java.util.List;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

public interface QueueItemDao {
  List<QueueItem> findAll();
  QueueItem findById(Long id);
  List<QueueItem> findByQueue(Queue queue);
  void save(QueueItem queueItem);
  void delete(QueueItem queueItem);
}
