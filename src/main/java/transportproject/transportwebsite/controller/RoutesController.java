package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.model.RouteStop;
import transportproject.transportwebsite.model.transport.TransportType;

import java.util.List;

@Controller
public class RoutesController {

    private final RouteStopDAO routeStopDAO;

    @Autowired
    public RoutesController(RouteStopDAO routeStopDAO) {
        this.routeStopDAO = routeStopDAO;
    }

    //TODO create exception handler for wrong url
    @GetMapping(value = "/transport/{type}/{routeNumber}")
    public String routeListPage(Model model, @PathVariable("type") TransportType type, @PathVariable("routeNumber") Integer routeNumber) {
        System.out.println(type.name() + routeNumber);
        final List<RouteStop> routeStops = routeStopDAO.getRouteStopsByTransportTypeAndRouteNumber(type, routeNumber);
        System.out.println(routeStops);
        return "test";
    }

}
