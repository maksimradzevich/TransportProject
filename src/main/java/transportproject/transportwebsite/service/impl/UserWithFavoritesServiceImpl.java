package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transportproject.transportwebsite.business.user.User;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.business.user.UserWithFavorites;
import transportproject.transportwebsite.business.user.UserWithFavoritesImpl;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.UserWithFavoritesService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

@Component
public class UserWithFavoritesServiceImpl implements UserWithFavoritesService {

    private final UserService userService;
    private final TransportDTODAO transportDTODAO;
    private final StopDTODAO stopDTODAO;

    @Autowired
    public UserWithFavoritesServiceImpl(UserService userService, TransportDTODAO transportDTODAO, StopDTODAO stopDTODAO) {
        this.userService = userService;
        this.transportDTODAO = transportDTODAO;
        this.stopDTODAO = stopDTODAO;
    }

    @Override
    public UserWithFavorites findActiveUser() throws NotFoundException {
        final User user = userService.findActiveUser();
        return new UserWithFavoritesImpl(user, stopDTODAO, transportDTODAO);
    }
}
