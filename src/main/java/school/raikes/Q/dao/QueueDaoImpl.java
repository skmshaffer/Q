package school.raikes.Q.dao;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.model.User;

@Repository
public class QueueDaoImpl implements QueueDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public QueueDaoImpl(EntityManagerFactory emf) {
        this.sessionFactory = emf.unwrap(SessionFactory.class);
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
    public List<Queue> findByOwner(User owner) {
        List<Queue> queues;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<Queue> cq = cb.createQuery(Queue.class);
            Root<Queue> r = cq.from(Queue.class);
            cq.select(r).where(cb.equal(r.get("owner"), owner));

            Query<Queue> q = s.createQuery(cq);

            queues = q.getResultList();
        } catch (Exception ex) {
            queues = null;
            //TODO: Handle this somehow.
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
    public Queue findByQueueCode(String queueCode) {
        Queue queue;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

            CriteriaQuery<Queue> cq = cb.createQuery(Queue.class);
            Root<Queue> r = cq.from(Queue.class);
            cq.select(r).where(cb.equal(r.get("queueCode"), queueCode));

            Query<Queue> q = s.createQuery(cq);

            queue = q.getSingleResult();
        } catch (Exception ex) {
            queue = null;
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

            Queue toDelete = s.load(Queue.class, queue.getId());
            s.delete(toDelete);

            s.getTransaction().commit();
        }
    }
}
