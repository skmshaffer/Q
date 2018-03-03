package school.raikes.Q.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import school.raikes.Q.model.Role;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(EntityManagerFactory emf) {
        this.sessionFactory = emf.unwrap(SessionFactory.class);
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<Role> cq = cb.createQuery(Role.class);
            Root<Role> r = cq.from(Role.class);
            cq.select(r);

            Query<Role> q = s.createQuery(cq);

            roles = q.getResultList();
        }

        return roles;
    }

    @Override
    public Role findById(Long id) {
        Role role;

        try (Session s = sessionFactory.openSession()) {
            role = s.get(Role.class, id);
        }

        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role;

        try (Session s = sessionFactory.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();

            CriteriaQuery<Role> cq = cb.createQuery(Role.class);
            Root<Role> r = cq.from(Role.class);
            cq.select(r).where(cb.equal(r.get("name"), name));

            Query<Role> q = s.createQuery(cq);

            role = q.getSingleResult();
        }

        return role;
    }

    @Override
    public void save(Role role) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.saveOrUpdate(role);

            s.getTransaction().commit();
        }
    }

    @Override
    public void delete(Role role) {
        try (Session s = sessionFactory.openSession()) {
            s.beginTransaction();

            s.delete(role);

            s.getTransaction().commit();
        }
    }
}
