package transportproject.transportwebsite.service;

import transportproject.transportwebsite.business.user.UserWithFavorites;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

public interface UserWithFavoritesService {

    UserWithFavorites findActiveUser() throws NotFoundException;
}
