package transportproject.transportwebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;

import java.util.Arrays;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDAO.findByEmail(username);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Arrays.asList(grantedAuthority)
        );
        return userDetails;
    }
}
