package transportproject.transportwebsite.business.user;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.exceptions.UserExistsException;

public class UserImpl implements User {

    private final UserDTO userDTO;
    private final UserDTODAO userRepository;

    public UserImpl(UserDTO userDTO, UserDTODAO userRepository) {
        this.userDTO = userDTO;
        this.userRepository = userRepository;
    }

    @Override
    public void save() {
        userRepository.save(userDTO);
    }

    @Override
    public void register() throws UserExistsException {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserExistsException("This email already exists");
        }
        userDTO.setRole("ROLE_user");
        userRepository.save(userDTO);
    }

    @Override
    public UserDTO getInnerState() {
        return userDTO;
    }
}
