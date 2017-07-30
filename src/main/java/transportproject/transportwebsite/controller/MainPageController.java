package transportproject.transportwebsite.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.dto.StopDTO;
import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.dto.TransportType;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
public class MainPageController {

    private static final String MAIN_PAGE_TEMPLATE = "index";
    private static final String MAIN_PAGE_MAPPING = "/";


    private final TransportDTODAO transportDTODAO;
    private final UserDTODAO userDTODAO;

    @Autowired
    public MainPageController(TransportDTODAO transportDTODAO, UserDTODAO userDTODAO) {
        this.transportDTODAO = transportDTODAO;
        this.userDTODAO = userDTODAO;
    }

    /**
     * index page controller method
     *
     * @return main page template
     */
    @GetMapping(MAIN_PAGE_MAPPING)
    public String mainPage(Model model, Principal principal) {

        List<StopDTO> favoriteStopDTOS = new ArrayList<>();
        List<TransportDTO> favoriteTransportDTO = new ArrayList<>();
        final List<TransportDTO> buses = transportDTODAO.findTransportByType(TransportType.BUS);
        final List<TransportDTO> trolleybuses = transportDTODAO.findTransportByType(TransportType.TROLLEYBUS);
        if (principal != null) {
            final UserDTO userDTO = userDTODAO.findByEmail(principal.getName());
            favoriteStopDTOS = userDTO.getFavoriteStopDTOS();
            Hibernate.initialize(favoriteStopDTOS);
            favoriteTransportDTO = userDTO.getFavoriteTransportDTO();
            Hibernate.initialize(favoriteTransportDTO);
        }
        model.addAttribute("favStops", favoriteStopDTOS);
        model.addAttribute("favTransport", favoriteTransportDTO);
        model.addAttribute("buses", buses);
        model.addAttribute("trolleybuses",trolleybuses);
        return MAIN_PAGE_TEMPLATE;
    }
}
