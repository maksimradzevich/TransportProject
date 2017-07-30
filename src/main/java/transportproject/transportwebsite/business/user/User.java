package transportproject.transportwebsite.business.user;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.exceptions.UserExistsException;

public interface User {

    void save();

    void register() throws UserExistsException;

    UserDTO getInnerState();
}
