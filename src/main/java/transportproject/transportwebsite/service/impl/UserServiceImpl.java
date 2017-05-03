package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;ngframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register
        INFO: Mapped "{[/test],methods=[GET]}" onto public java.lang.String transportproject.transportwebsite.test.TestController.testTransport()
        апр 29, 2017 11:21:32 PM org.springframework.web.servlet.handler.SimpleUrlHandlerMapping registerHandler
        INFO: Mapped URL path [/static/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
         апр 29, 2017 11:21:33 PM org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter initControllerAdviceCache
         INFO: Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Sat Apr 29 23:21:29 MSK 2017]; root of context hierarchy
         SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
         SLF4J: Defaulting to no-operation (NOP) logger implementation
         SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
         апр 29, 2017 11:21:33 PM org.springframework.web.context.ContextLoader initWebApplicationContext
         INFO: Root WebApplicationContext: initialization completed in 4341 ms
         апр 29, 2017 11:21:33 PM org.springframework.web.servlet.DispatcherServlet initServletBean
         INFO: FrameworkServlet 'dispatcher': initialization started
         [INFO] Initializing Spring FrameworkServlet 'dispatcher'
         апр 29, 2017 11:21:33 PM org.springframework.web.context.support.AnnotationConfigWebApplicationContext prepareRefresh
         INFO: Refreshing WebApplicationContext for namespace 'dispatcher-servlet': startup date [Sat Apr 29 23:21:33 MSK 2017]; parent: Root WebApplicationContext
         апр 29, 2017 11:21:33 PM org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor <init>
         INFO: JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
         апр 29, 2017 11:21:33 PM org.springframework.web.servlet.DispatcherServlet initServletBean
         [INFO] Started o.e.j.m.p.JettyWebAppContext@2401856{/,file:///Users/maximradevich/IdeaProjects/TransportProject/src/main/webapp/,AVAILABLE}{file:///Users/maximradevich/IdeaProjects/TransportProject/src/main/webapp/}
         INFO: FrameworkServlet 'dispatcher': initialization completed in 53 ms
         [INFO] Started ServerConnector@47666b39{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
         [INFO] Started @12065ms
         [INFO] Started Jetty Server
         Hibernate:
         select
         this_.transport_id as transpor1_3_0_,
         this_.route_number as route_nu2_3_0_,
         this_.type as type3_3_0_
         from
         Transport this_
         where
         this_.type=?
         Hibernate:
    }

    private static String getActiveUserEmail() {
        String userEmail;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = (String) principal;
        }
        return userEmail;
    }

    @Override
    public User findActiveUser() {
        final String principalEmail = getActiveUserEmail();
        return userDAO.findByEmail(principalEmail);
    }
}
