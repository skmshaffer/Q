package school.raikes.Q.service;

import school.raikes.Q.model.User;
import school.raikes.Q.model.dto.RegistrationInfo;
import school.raikes.Q.service.exceptions.UserRegistrationException;

public interface SecurityService {

    User registerNewUser(RegistrationInfo info) throws UserRegistrationException;

}
