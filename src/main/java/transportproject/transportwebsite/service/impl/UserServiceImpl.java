package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static String getActiveUserEmail() {
        String userEmail;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = (String) principal;
        }
        return userEmail;
    }

    @Override
    public User findActiveUser() {
        final String principalEmail = getActiveUserEmail();
        if (principalEmail != null) {
            return userDAO.findByEmail(principalEmail);
        }
        return null;
    }
}
