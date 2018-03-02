package school.raikes.Q.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class QueueItemDaoImpl implements QueueItemDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public QueueItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<QueueItem> findAll() {
        List<QueueItem> queueItems;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<QueueItem> cq = cb.createQuery(QueueItem.class);
            Root<QueueItem> r = cq.from(QueueItem.class);
            cq.select(r);

            Query<QueueItem> q = s.createQuery(cq);

            queueItems = q.getResultList();
        }

        return queueItems;
    }

    @Override
    public QueueItem findById(Long id) {
        QueueItem queueItem;

        try (Session s = sessionFactory.openSession()) {
            queueItem = s.get(QueueItem.class, id);
        }

        return queueItem;
    }

    @Override
    public List<QueueItem> findByQueue(Queue queue) {
        List<QueueItem> queueItems;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<QueueItem> cq = cb.createQuery(QueueItem.class);
            Root<QueueItem> r = cq.from(QueueItem.class);
            cq.select(r).where(cb.equal(r.get("queue_id"), queue.getId()));

            Query<QueueItem> q = s.createQuery(cq);

            queueItems = q.getResultList();
        }

        return queueItems;
    }

    @Override
    public List<QueueItem> findByCompleted(boolean completed) {
        List<QueueItem> queueItems;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<QueueItem> cq = cb.createQuery(QueueItem.class);
            Root<QueueItem> r = cq.from(QueueItem.class);
            cq.select(r).where(cb.equal(r.get("completed"), completed));

            Query<QueueItem> q = s.createQuery(cq);

            queueItems = q.getResultList();
        }

        return queueItems;
    }

    @Override
    public void save(QueueItem queueItem) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.saveOrUpdate(queueItem);

            s.getTransaction().commit();
        }
    }

    @Override
    public void delete(QueueItem queueItem) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.delete(queueItem);

            s.getTransaction().commit();
        }
    }
}
