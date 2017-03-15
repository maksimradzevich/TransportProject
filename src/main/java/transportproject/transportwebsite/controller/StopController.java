package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transportproject.transportwebsite.dao.RouteStopDAO;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.model.RouteStop;

@Controller
public class StopController {

    private final StopDAO stopDAO;
    private final RouteStopDAO routeStopDAO;

    @Autowired
    public StopController(StopDAO stopDAO, RouteStopDAO routeStopDAO) {
        this.stopDAO = stopDAO;
        this.routeStopDAO = routeStopDAO;
    }

    @GetMapping("/stop/{stopId}/{routeId}")
    public String stopPageForRoute(Model model, @PathVariable("stopId") Integer stopId, @PathVariable("routeId") Integer routeId) {
        final RouteStop routeStop = routeStopDAO.getRouteStopByRouteIdAndStopId(routeId, stopId);
//        System.out.println(routeStop.getTimetable());
        final String timetable = routeStop.getTimetable();
        model.addAttribute("timetable", timetable);
        return "stop";
    }
}
