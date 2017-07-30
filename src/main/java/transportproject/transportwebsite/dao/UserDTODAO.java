package transportproject.transportwebsite.dao;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.dto.UserDTO;

public interface UserDTODAO extends CrudRepository<UserDTO, Integer> {
    UserDTO findByEmail(String email);
}
