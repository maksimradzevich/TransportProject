package transportproject.transportwebsite.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.service.FavoriteService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final StopDAO stopDAO;
    private final UserDAO userDAO;

    @Autowired
    public FavoriteServiceImpl(StopDAO stopDAO, UserDAO userDAO) {
        this.stopDAO = stopDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void addFavoriteStop(Integer stopId) {
        final Stop stop = stopDAO.findOne(stopId);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = userDAO.findByEmail(authentication.getName());
        final List<Stop> favoriteStops = user.getFavoriteStops();
        Hibernate.initialize(favoriteStops);
        favoriteStops.add(stop);
        userDAO.save(user);
    }
}
