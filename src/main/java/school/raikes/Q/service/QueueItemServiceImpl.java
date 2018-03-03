package school.raikes.Q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.raikes.Q.dao.QueueItemDao;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueItemServiceImpl implements QueueItemService {

    private final QueueItemDao queueItemDao;

    @Autowired
    public QueueItemServiceImpl(QueueItemDao queueItemDao) {
        this.queueItemDao = queueItemDao;
    }

    @Override
    public List<QueueItem> findAll() {
        return queueItemDao.findAll();
    }

    @Override
    public QueueItem findById(Long id) {
        return queueItemDao.findById(id);
    }

    @Override
    public List<QueueItem> findByQueue(Queue queue) {
        return queueItemDao.findByQueue(queue);
    }

    @Override
    public List<QueueItem> findByCompleted(boolean completed) {
        return queueItemDao.findByCompleted(completed);
    }

    @Override
    public List<QueueItem> findByCompleted(Queue queue, boolean completed) {
        return queueItemDao.findByQueue(queue).stream().filter(item -> item.isComplete() == completed).collect(Collectors.toList());
    }

    @Override
    public void save(QueueItem queueItem) {
        queueItemDao.save(queueItem);
    }

    @Override
    public void delete(QueueItem queueItem) {
        queueItemDao.delete(queueItem);
    }
}
