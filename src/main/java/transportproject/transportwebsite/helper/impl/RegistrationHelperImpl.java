package transportproject.transportwebsite.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.helper.RegistrationHelper;
import transportproject.transportwebsite.helper.exceptions.UserExistsException;
import transportproject.transportwebsite.model.User;

@Service("registrationService")
public class RegistrationHelperImpl implements RegistrationHelper {

    private final UserDAO userDAO;

    @Autowired
    public RegistrationHelperImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registerUser(User user) throws UserExistsException {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            throw new UserExistsException("This email already exists");
        }
        user.setRole("ROLE_user");
        userDAO.save(user);
    }
}
