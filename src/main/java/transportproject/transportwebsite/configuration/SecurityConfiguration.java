package transportproject.transportwebsite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ComponentScan("transportproject.transportwebsite")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(SpringMvcConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    private final UserDetailsService authenticationService;

    @Autowired
    public SecurityConfiguration(UserDetailsService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(authenticationService);
        http
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login?error");
    }
}
