package transportproject.transportwebsite.business.user;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.business.stop.Stop;
import transportproject.transportwebsite.business.transport.Transport;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dto.FavoriteItem;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

public interface UserWithFavorites extends User {
    boolean isInFavorites(Stop stop);

    boolean isInFavorites(Transport transport);

    void removeFavorite(Stop stop);

    void removeFavorite(Transport transport);

    void addFavorite(Transport transport);

    void addFavorite(Stop stop);

    void addFavorite(FavoriteItem item) throws NotFoundException;

    void removeFavorite(FavoriteItem item) throws NotFoundException;
}
