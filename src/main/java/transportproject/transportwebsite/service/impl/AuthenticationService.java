package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;

import java.util.Arrays;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    private final UserDTODAO userDTODAO;

    @Autowired
    public AuthenticationService(UserDTODAO userDTODAO) {
        this.userDTODAO = userDTODAO;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        final UserDTO userDTO = userDTODAO.findByEmail(username);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDTO.getRole());
        UserDetails userDetails = new User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                Arrays.asList(grantedAuthority)
        );
        return userDetails;
    }
}
