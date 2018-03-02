package school.raikes.Q.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;

public class QueueDaoImpl implements QueueDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Queue> findAll() {
    return null;
  }

  @Override
  public Queue findById(Long id) {
    return null;
  }

  @Override
  public Queue findByQueueItem(QueueItem queueItem) {
    return null;
  }

  @Override
  public void save(Queue queue) {

  }

  @Override
  public void delete(Queue queue) {

  }
}
