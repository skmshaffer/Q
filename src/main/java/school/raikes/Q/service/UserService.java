package school.raikes.Q.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import school.raikes.Q.model.Role;
import school.raikes.Q.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    List<User> findByRole(Role role);

    void save(User user);

    void delete(User user);
}
