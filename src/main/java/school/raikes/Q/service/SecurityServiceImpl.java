package school.raikes.Q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.raikes.Q.dao.RoleDao;
import school.raikes.Q.dao.UserDao;
import school.raikes.Q.model.User;
import school.raikes.Q.model.dto.RegistrationInfo;
import school.raikes.Q.service.exceptions.UserRegistrationException;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private RoleDao roleDao;

    @Autowired
    public SecurityServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Override
    public User registerNewUser(RegistrationInfo info) throws UserRegistrationException {
        User existingUser = userDao.findByUsername(info.getUsername());

        if (existingUser != null) {
            throw new UserRegistrationException("Username already taken!");
        }

        if (!info.getPassword().equals(info.getVerifyPassword())) {
            throw new UserRegistrationException("Passwords do not match.");
        }

        User user = new User();

        user.setUsername(info.getUsername());
        user.setEnabled(true);
        user.setRole(roleDao.findById(1L));
        user.setPassword(passwordEncoder.encode(info.getPassword()));

        userDao.save(user);

        return user;
    }
}
