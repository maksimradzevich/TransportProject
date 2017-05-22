package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.dao.RouteDAO;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.RouteStop;
import transportproject.transportwebsite.model.transport.Transport;
import transportproject.transportwebsite.model.transport.TransportType;
import transportproject.transportwebsite.service.FavoriteService;
import transportproject.transportwebsite.service.RouteService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;

@Controller
public class RoutesController {

    private final RouteStopDAO routeStopDAO;
    private final RouteDAO routeDAO;
    private final RouteService routeService;
    private final TransportDAO transportDAO;
    private final FavoriteService favoriteService;

    @Autowired
    public RoutesController(RouteStopDAO routeStopDAO, RouteDAO routeDAO, RouteService routeService, TransportDAO transportDAO, FavoriteService favoriteService) {
        this.routeStopDAO = routeStopDAO;
        this.routeDAO = routeDAO;
        this.routeService = routeService;
        this.transportDAO = transportDAO;
        this.favoriteService = favoriteService;
    }

    @GetMapping(value = "/transport/{type}/{routeNumber}")
    public String routeListPage(Model model, @PathVariable("type") TransportType type, @PathVariable("routeNumber") Integer routeNumber) throws NotFoundException {

        final List<Route> routes = routeService.getRoutesByNumberAndTransportType(routeNumber, type);

        final Route route1 = routes.get(0);
        final Route route2 = routes.get(1);

        final String route1Name = route1.getName();
        final String route2Name = route2.getName();

        final List<RouteStop> routeStops1 = routeStopDAO.getRouteStopByRouteId(route1.getRouteId());
        final List<RouteStop> routeStops2 = routeStopDAO.getRouteStopByRouteId(route2.getRouteId());

        final Transport transport = transportDAO.findTransportByRouteNumberAndType(routeNumber, type);

        boolean isInFavorites = favoriteService.isInFavorites(transport);
        model.addAttribute("inFavorites", isInFavorites);

        model.addAttribute("transport", transport);
        model.addAttribute("routeStops1", routeStops1);
        model.addAttribute("routeStops2", routeStops2);
        model.addAttribute("route1Name", route1Name);
        model.addAttribute("route2Name", route2Name);

        return "routes";
    }

}
