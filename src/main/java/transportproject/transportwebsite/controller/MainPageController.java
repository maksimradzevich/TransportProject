package transportproject.transportwebsite.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.model.User;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.model.transport.Transport;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
public class MainPageController {

    private static final String MAIN_PAGE_TEMPLATE = "index";
    private static final String MAIN_PAGE_MAPPING = "/";


    private final TransportDAO transportDAO;
    private final UserDAO userDAO;

    @Autowired
    public MainPageController(TransportDAO transportDAO, UserDAO userDAO) {
        this.transportDAO = transportDAO;
        this.userDAO = userDAO;
    }

    /**
     * index page controller method
     *
     * @return main page template
     */
    @GetMapping(MAIN_PAGE_MAPPING)
    public String mainPage(Model model, Principal principal) {

        List<Stop> favoriteStops = new ArrayList<>();
        final List<Transport> buses = transportDAO.findTransportByType(TransportType.BUS);
        final List<Transport> trolleybuses = transportDAO.findTransportByType(TransportType.TROLLEYBUS);
        if (principal != null) {
            final User user = userDAO.findByEmail(principal.getName());
            favoriteStops = user.getFavoriteStops();
            Hibernate.initialize(favoriteStops);
        }
        model.addAttribute("favStops", favoriteStops);
        model.addAttribute("buses", buses);
        model.addAttribute("trolleybuses",trolleybuses);
        return MAIN_PAGE_TEMPLATE;
    }
}
