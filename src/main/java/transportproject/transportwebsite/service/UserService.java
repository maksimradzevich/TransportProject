package transportproject.transportwebsite.service;

import transportproject.transportwebsite.business.user.User;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

public interface UserService {
    User findActiveUser() throws NotFoundException;
}
