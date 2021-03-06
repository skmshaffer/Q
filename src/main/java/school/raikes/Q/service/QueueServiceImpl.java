package school.raikes.Q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.raikes.Q.dao.QueueDao;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.model.User;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueueDao queueDao;

    @Autowired
    public QueueServiceImpl(QueueDao queueDao) {
        this.queueDao = queueDao;
    }

    @Override
    public List<Queue> findAll() {
        return queueDao.findAll();
    }

    @Override
    public List<Queue> findByOwner(User owner) {
        return queueDao.findByOwner(owner);
    }

    @Override
    public Queue findById(Long id) {
        return queueDao.findById(id);
    }

    @Override
    public Queue findByQueueItem(QueueItem queueItem) {
        return queueDao.findByQueueItem(queueItem);
    }

    public Queue findByQueueCode(String queueCode) {
        queueCode = queueCode.toUpperCase();
        return queueDao.findByQueueCode(queueCode);
    }

    @Override
    public void save(Queue queue) {
        queue.setQueueCode(queue.getQueueCode().toUpperCase());
        queueDao.save(queue);
    }

    @Override
    public void delete(Queue queue) {
        queueDao.delete(queue);
    }
}
