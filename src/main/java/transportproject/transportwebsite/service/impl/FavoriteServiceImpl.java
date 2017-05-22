package transportproject.transportwebsite.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.model.transport.Transport;
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
    private final TransportDAO transportDAO;

    @Autowired
    public FavoriteServiceImpl(StopDAO stopDAO, UserDAO userDAO, UserService userService, TransportDAO transportDAO) {
        this.stopDAO = stopDAO;
        this.userDAO = userDAO;
        this.userService = userService;
        this.transportDAO = transportDAO;
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
    public boolean isInFavorites(Object o) {
        final User activeUser = userService.findActiveUser();
        if (activeUser != null) {
            if (o.getClass().getSimpleName().equalsIgnoreCase("stop")){
                final List<Stop> favoriteStops = activeUser.getFavoriteStops();
                Hibernate.initialize(favoriteStops);
                return favoriteStops.contains((Stop) o);
            } else if (o.getClass().getSimpleName().equalsIgnoreCase("transport")) {
                final List<Transport> favoriteTransport = activeUser.getFavoriteTransport();
                Hibernate.initialize(favoriteTransport);
                return favoriteTransport.contains((Transport) o);
            }

        }
        return false;
    }

    @Override
    public void removeFavoriteStop(Integer id) {
        final Stop stop = stopDAO.findOne(id);
        final User user = userService.findActiveUser();
        final List<Stop> favoriteStops = user.getFavoriteStops();
        Hibernate.initialize(favoriteStops);
        favoriteStops.remove(stop);
        userDAO.save(user);
    }

    @Override
    public void addFavoriteTransport(Integer id) {
        final Transport transport = transportDAO.findById(id);
        final User activeUser = userService.findActiveUser();
        final List<Transport> favoriteTransport = activeUser.getFavoriteTransport();
        Hibernate.initialize(favoriteTransport);
        favoriteTransport.add(transport);
        userDAO.save(activeUser);
    }

    @Override
    public void removeFavoriteTransport(Integer id) {
        final Transport transport = transportDAO.findById(id);
        final User user = userService.findActiveUser();
        final List<Transport> favoriteTransport = user.getFavoriteTransport();
        Hibernate.initialize(favoriteTransport);
        favoriteTransport.remove(transport);
        userDAO.save(user);
    }
}
