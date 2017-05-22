package transportproject.transportwebsite.service;

public interface FavoriteService {
    void addFavoriteStop(Integer stopId);

    boolean isInFavorites(Object o);

    void removeFavoriteStop(Integer id);

    void addFavoriteTransport(Integer id);

    void removeFavoriteTransport(Integer id);
}
