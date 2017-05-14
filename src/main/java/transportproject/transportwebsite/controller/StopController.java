package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.service.StopService;
import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.RouteStop;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.model.transport.Transport;

import java.util.List;
import java.util.Map;

@Controller
public class StopController {

    private final StopDAO stopDAO;
    private final RouteStopDAO routeStopDAO;
    private final StopService stopService;

    @Autowired
    public StopController(StopDAO stopDAO, RouteStopDAO routeStopDAO, StopService stopService) {
        this.stopDAO = stopDAO;
        this.routeStopDAO = routeStopDAO;
        this.stopService = stopService;
    }

    @GetMapping("/stop/{stopId}")
    public String stopPage(@PathVariable("stopId") Integer stopId, Model model) {

        List<RouteStop> routeStops = routeStopDAO.getRouteStopsByStopId(stopId);
        final Stop stop = stopDAO.findOne(stopId);

        model.addAttribute("rStops", routeStops);
        model.addAttribute("stop", stop);

        return "stop_all";
    }

    @GetMapping("/stop/{stopId}/{routeId}")
    public String stopPageForRoute(Model model, @PathVariable("stopId") Integer stopId, @PathVariable("routeId") Integer routeId) {

        final RouteStop routeStop = routeStopDAO.getRouteStopByRouteIdAndStopId(routeId, stopId);
        final String timetable = routeStop.getTimetable();
        final Route route = routeStop.getRoute();
        final Transport transport = route.getTransport();
        final Integer routeNumber = transport.getRouteNumber();
        final String nameOfTransport = transport.getType().getName();
        final String routeName = route.getName();
        final Stop stop = routeStop.getStop();

        model.addAttribute("timetable", timetable);
        model.addAttribute("transportName", nameOfTransport);
        model.addAttribute("transportNumber", routeNumber);
        model.addAttribute("routeName", routeName);
        model.addAttribute("stopName", stop.getName());
        return "stop";
    }

    @GetMapping("/stops")
    public String stopsPage(Model model) {
        final Map<Character, List<Stop>> sortedStops = stopService.getSortedStops();
        model.addAttribute("stops", sortedStops);
        return "stops";
    }
}
