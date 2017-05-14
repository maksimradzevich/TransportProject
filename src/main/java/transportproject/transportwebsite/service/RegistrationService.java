package transportproject.transportwebsite.service;

import transportproject.transportwebsite.service.exceptions.UserExistsException;
import transportproject.transportwebsite.model.User;

public interface RegistrationService {
    void registerUser(User user) throws UserExistsException;
}
