package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.model.User;

public interface UserDAO {
    User findByUsername(String username);
}
