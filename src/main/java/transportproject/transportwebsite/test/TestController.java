package transportproject.transportwebsite.test;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dao.TransportDAO;
import transportproject.transportwebsite.model.Route;
import transportproject.transportwebsite.model.RouteStop;
import transportproject.transportwebsite.model.Stop;
import transportproject.transportwebsite.model.Transport;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class TestController {
    private final TransportDAO transportDAO;

    @Autowired
    public TestController(TransportDAO transportDAO) {
        this.transportDAO = transportDAO;
    }

    @GetMapping("/test")
    public String testTransport() {
        final Transport transport = transportDAO.findTransportByRouteNumberAndTypeWithRoutes(1, TransportType.BUS);
        System.out.println(transport);
        System.out.println(transport.getRoutes());

        final Route route = transport.getRoutes().get(0);
        Hibernate.initialize(route);
        final List<RouteStop> stops = route.getRouteStops();
        Hibernate.initialize(stops);
        System.out.println(stops);
        return "test";
    }
}
