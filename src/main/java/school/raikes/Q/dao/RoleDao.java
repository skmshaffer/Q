package school.raikes.Q.dao;

import java.util.List;
import school.raikes.Q.model.Role;

public interface RoleDao {
  List<Role> findAll();
  Role findById(Long id);
  Role findByName(String name);
  void save(Role role);
  void delete(Role role);
}
