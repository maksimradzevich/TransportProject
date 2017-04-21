package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.dao.RouteDAO;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.model.Route;
import transportproject.transportwebsite.model.RouteStop;
import transportproject.transportwebsite.model.transport.TransportType;

import java.util.List;

@Controller
public class RoutesController {

    private final RouteStopDAO routeStopDAO;
    private final RouteDAO routeDAO;

    @Autowired
    public RoutesController(RouteStopDAO routeStopDAO, RouteDAO routeDAO) {
        this.routeStopDAO = routeStopDAO;
        this.routeDAO = routeDAO;
    }

    //TODO create exception handler for wrong url
    @GetMapping(value = "/transport/{type}/{routeNumber}")
    public String routeListPage(Model model, @PathVariable("type") TransportType type, @PathVariable("routeNumber") Integer routeNumber) {

        final List<Route> routes = routeDAO.getRoutesByRouteNumberAndTransportType(routeNumber, type);

        final Route route1 = routes.get(0);
        final Route route2 = routes.get(1);

        final String route1Name = route1.getName();
        final String route2Name = route2.getName();

        System.out.println(routes);

        final List<RouteStop> routeStops1 = routeStopDAO.getRouteStopByRouteId(route1.getRouteId());
        final List<RouteStop> routeStops2 = routeStopDAO.getRouteStopByRouteId(route2.getRouteId());

        model.addAttribute("routeStops1", routeStops1);
        model.addAttribute("routeStops2", routeStops2);
        model.addAttribute("route1Name", route1Name);
        model.addAttribute("route2Name", route2Name);

        return "routes";
    }

}
