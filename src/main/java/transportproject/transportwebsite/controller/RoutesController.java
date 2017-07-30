package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.business.transport.Transport;
import transportproject.transportwebsite.business.user.UserWithFavorites;
import transportproject.transportwebsite.business.user.UserWithFavoritesImpl;
import transportproject.transportwebsite.dao.RouteDAO;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.RouteStop;
import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.dto.TransportType;
import transportproject.transportwebsite.service.RouteService;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.UserWithFavoritesService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;

@Controller
public class RoutesController {

    private final RouteStopDAO routeStopDAO;
    private final RouteDAO routeDAO;
    private final RouteService routeService;
    private final TransportDTODAO transportDTODAO;
    private final UserWithFavoritesService userService;
    private final StopDTODAO stopDTODAO;

    @Autowired
    public RoutesController(RouteStopDAO routeStopDAO, RouteDAO routeDAO, RouteService routeService, TransportDTODAO transportDTODAO, UserWithFavoritesService userService, StopDTODAO stopDTODAO) {
        this.routeStopDAO = routeStopDAO;
        this.routeDAO = routeDAO;
        this.routeService = routeService;
        this.transportDTODAO = transportDTODAO;

        this.userService = userService;
        this.stopDTODAO = stopDTODAO;
    }

    @GetMapping(value = "/transport/{type}/{routeNumber}")
    public String routeListPage(Model model, @PathVariable("type") TransportType type, @PathVariable("routeNumber") Integer routeNumber) throws NotFoundException {

        final List<RouteDTO> routeDTOS = routeService.getRoutesByNumberAndTransportType(routeNumber, type);

        final RouteDTO routeDTO1 = routeDTOS.get(0);
        final RouteDTO routeDTO2 = routeDTOS.get(1);

        final String route1Name = routeDTO1.getName();
        final String route2Name = routeDTO2.getName();

        final List<RouteStop> routeStops1 = routeStopDAO.getRouteStopByRouteId(routeDTO1.getRouteId());
        final List<RouteStop> routeStops2 = routeStopDAO.getRouteStopByRouteId(routeDTO2.getRouteId());

        final TransportDTO transportDTO = transportDTODAO.findTransportByRouteNumberAndType(routeNumber, type);

        final UserWithFavorites user = userService.findActiveUser();
        boolean isInFavorites = user.isInFavorites(new Transport(transportDTO));
        model.addAttribute("inFavorites", isInFavorites);

        model.addAttribute("transport", transportDTO);
        model.addAttribute("routeStops1", routeStops1);
        model.addAttribute("routeStops2", routeStops2);
        model.addAttribute("route1Name", route1Name);
        model.addAttribute("route2Name", route2Name);

        return "routes";
    }

}
