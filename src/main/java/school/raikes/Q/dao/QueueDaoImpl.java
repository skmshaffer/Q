package school.raikes.Q.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

@Repository
public class QueueDaoImpl implements QueueDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public QueueDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Queue> findAll() {
        List<Queue> queues;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<Queue> cq = cb.createQuery(Queue.class);
            Root<Queue> r = cq.from(Queue.class);
            cq.select(r);

            Query<Queue> q = s.createQuery(cq);

            queues = q.getResultList();
        }

        return queues;
    }

    @Override
    public Queue findById(Long id) {
        Queue queue;

        try (Session s = sessionFactory.openSession()) {
            queue = s.get(Queue.class, id);
        }

        return queue;
    }

    @Override
    public Queue findByQueueItem(QueueItem queueItem) {
        Queue queue;

        try (Session s = sessionFactory.openSession()) {
            queue = s.get(Queue.class, queueItem.getQueue().getId());
        }

        return queue;
    }

    @Override
    public void save(Queue queue) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.saveOrUpdate(queue);

            s.getTransaction().commit();
        }
    }

    @Override
    public void delete(Queue queue) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.delete(queue);

            s.getTransaction().commit();
        }
    }
}
