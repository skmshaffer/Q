package school.raikes.Q.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import school.raikes.Q.model.Role;
import school.raikes.Q.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory emf) {
        this.sessionFactory = emf.unwrap(SessionFactory.class);
    }

    @Override
    public List<User> findAll() {
        List<User> users;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> r = cq.from(User.class);
            cq.select(r);

            Query<User> q = s.createQuery(cq);

            users = q.getResultList();
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        User user;

        try (Session s = sessionFactory.openSession()) {
            user = s.get(User.class, id);
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> r = cq.from(User.class);
            cq.select(r).where(cb.equal(r.get("username"), username));

            Query<User> q = s.createQuery(cq);

            user = q.getSingleResult();
        }

        return user;
    }

    @Override
    public List<User> findByRole(Role role) {
        List<User> users;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> r = cq.from(User.class);
            cq.select(r).where(cb.equal(r.get("role_id"), role.getId()));

            Query<User> q = s.createQuery(cq);

            users = q.getResultList();
        }

        return users;
    }

    @Override
    public void save(User user) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.saveOrUpdate(user);

            s.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.delete(user);

            s.getTransaction().commit();
        }
    }
}
