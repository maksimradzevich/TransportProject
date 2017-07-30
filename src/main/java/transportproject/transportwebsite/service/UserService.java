package transportproject.transportwebsite.service;

import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

public interface UserService {
    UserImpl findActiveUser() throws NotFoundException;
}
