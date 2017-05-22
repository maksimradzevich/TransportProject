package transportproject.transportwebsite.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.service.FavoriteService;
import transportproject.transportwebsite.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final StopDAO stopDAO;
    private final UserDAO userDAO;
    private final UserService userService;

    @Autowired
    public FavoriteServiceImpl(StopDAO stopDAO, UserDAO userDAO, UserService userService) {
        this.stopDAO = stopDAO;
        this.userDAO = userDAO;
        this.userService = userService;
    }

    @Override
    public void addFavoriteStop(Integer stopId) {
        final Stop stop = stopDAO.findOne(stopId);
        final User user = userService.findActiveUser();
        final List<Stop> favoriteStops = user.getFavoriteStops();
        Hibernate.initialize(favoriteStops);
        favoriteStops.add(stop);
        userDAO.save(user);
    }

    @Override
    public boolean isInFavorites(Stop stop) {
        final User activeUser = userService.findActiveUser();
        if (activeUser != null) {
            final List<Stop> favoriteStops = activeUser.getFavoriteStops();
            Hibernate.initialize(favoriteStops);
            return favoriteStops.contains(stop);
        }
        return false;
    }
}
