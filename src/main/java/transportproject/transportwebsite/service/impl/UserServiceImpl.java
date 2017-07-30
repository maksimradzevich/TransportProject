package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.business.user.User;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final StopDTODAO stopDTODAO;
    private final UserDTODAO userDTODAO;
    private final TransportDTODAO transportDTODAO;

    @Autowired
    public UserServiceImpl(UserDTODAO userDTODAO, StopDTODAO stopDTODAO, TransportDTODAO transportDTODAO) {
        this.userDTODAO = userDTODAO;
        this.stopDTODAO = stopDTODAO;
        this.transportDTODAO = transportDTODAO;
    }

    private static String getActiveUserEmail() throws NotFoundException {
        String userEmail;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = (String) principal;
        }
        if (userEmail == null) {
            throw new NotFoundException("Email of active user not found");
        }
        return userEmail;
    }

    @Override
    public User findActiveUser() throws NotFoundException {
        String email = getActiveUserEmail();
        final UserDTO userDTO = findUserDTOByEmail(email);
        return new UserImpl(userDTO, userDTODAO);
    }



    private UserDTO findUserDTOByEmail(String email) throws NotFoundException {
        final UserDTO userDTO = userDTODAO.findByEmail(email);
        if (userDTO == null) {
            throw new NotFoundException("UserImpl with email " + email + " not found");
        }
        return userDTO;
    }
}
