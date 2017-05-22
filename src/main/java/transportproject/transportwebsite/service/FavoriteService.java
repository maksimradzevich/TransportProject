package transportproject.transportwebsite.service;

import transportproject.transportwebsite.model.transport.Stop;

public interface FavoriteService {
    void addFavoriteStop(Integer stopId);
    boolean isInFavorites(Stop stop);

    void removeFavoriteStop(Integer id);
}
