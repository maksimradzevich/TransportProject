package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.business.stop.Stop;
import transportproject.transportwebsite.business.stop.StopsAsSortedMap;
import transportproject.transportwebsite.business.stop.StopsAsSortedMapImpl;
import transportproject.transportwebsite.business.stop.StopsImpl;
import transportproject.transportwebsite.business.user.UserWithFavorites;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.RouteStop;
import transportproject.transportwebsite.dto.StopDTO;
import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.service.UserWithFavoritesService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

@Controller
public class StopController {

    private final StopDTODAO stopDTODAO;
    private final RouteStopDAO routeStopDAO;
    private final UserWithFavoritesService userService;

    @Autowired
    public StopController(StopDTODAO stopDTODAO, RouteStopDAO routeStopDAO, UserWithFavoritesService userService) {
        this.stopDTODAO = stopDTODAO;
        this.routeStopDAO = routeStopDAO;
        this.userService = userService;
    }

    @GetMapping("/stop/{stopId}")
    public String stopPage(@PathVariable("stopId") Integer stopId, Model model) {

        List<RouteStop> routeStops = routeStopDAO.getRouteStopsByStopId(stopId);
        final StopDTO stopDTO = stopDTODAO.findOne(stopId);
        final UserWithFavorites user;
        boolean isInFavorites = false;
        try {
            user = userService.findActiveUser();
            isInFavorites = user.isInFavorites(new Stop(stopDTO));
        } catch (NotFoundException e) {
            //TODO add something here
        }

        model.addAttribute("inFavorites", isInFavorites);
        model.addAttribute("rStops", routeStops);
        model.addAttribute("stop", stopDTO);

        return "stop_all";
    }

    @GetMapping("/stop/{stopId}/{routeId}")
    public String stopPageForRoute(Model model, @PathVariable("stopId") Integer stopId, @PathVariable("routeId") Integer routeId) {

        final RouteStop routeStop = routeStopDAO.getRouteStopByRouteIdAndStopId(routeId, stopId);
        final String timetable = routeStop.getTimetable();
        final RouteDTO routeDTO = routeStop.getRouteDTO();
        final TransportDTO transportDTO = routeDTO.getTransportDTO();
        final Integer routeNumber = transportDTO.getRouteNumber();
        final String nameOfTransport = transportDTO.getType().getName();
        final String routeName = routeDTO.getName();
        final StopDTO stopDTO = routeStop.getStopDTO();

        model.addAttribute("timetable", timetable);
        model.addAttribute("transportName", nameOfTransport);
        model.addAttribute("transportNumber", routeNumber);
        model.addAttribute("routeName", routeName);
        model.addAttribute("stop", stopDTO);
        return "stop";
    }

    @GetMapping("/stops")
    public String stopsPage(Model model) {
        final StopsAsSortedMap stops = new StopsAsSortedMapImpl(new StopsImpl(stopDTODAO));
        final Map<Character, List<Stop>> sortedStops = stops.get();
        model.addAttribute("stops", sortedStops);
        return "stops";
    }
}
