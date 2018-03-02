package school.raikes.Q.dao;

import java.util.List;

import school.raikes.Q.model.Role;
import school.raikes.Q.model.User;

public interface UserDao {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    List<User> findByRole(Role role);

    void save(User user);

    void delete(User user);
}
