package transportproject.transportwebsite.helper;

import transportproject.transportwebsite.helper.exceptions.UserExistsException;
import transportproject.transportwebsite.model.User;

public interface RegistrationHelper {
    void registerUser(User user) throws UserExistsException;
}
