package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.model.Transport;
import transportproject.transportwebsite.model.transport.TransportType;

import java.util.List;

@Controller
public class MainPageController {

    private static final String MAIN_PAGE_TEMPLATE = "index";
    private static final String MAIN_PAGE_MAPPING = "/";


    private final TransportDAO transportDAO;

    @Autowired
    public MainPageController(TransportDAO transportDAO) {
        this.transportDAO = transportDAO;
    }

    /**
     * index page controller method
     *
     * @return main page template
     */
    @GetMapping(MAIN_PAGE_MAPPING)
    public String mainPage(Model model) {

        final List<Transport> buses = transportDAO.findTransportByType(TransportType.BUS);
        final List<Transport> trolleybuses = transportDAO.findTransportByType(TransportType.TROLLEYBUS);

        model.addAttribute("buses", buses);
        model.addAttribute("trolleybuses",trolleybuses);
        return MAIN_PAGE_TEMPLATE;
    }
}
