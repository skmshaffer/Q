package school.raikes.Q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.raikes.Q.dao.RoleDao;
import school.raikes.Q.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }
}
