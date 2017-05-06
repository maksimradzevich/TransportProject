package transportproject.transportwebsite.dao;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.model.User;

public interface UserDAO extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
