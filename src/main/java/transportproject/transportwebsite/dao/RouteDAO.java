package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.model.Route;
import transportproject.transportwebsite.model.transport.TransportType;

import java.util.List;

public interface RouteDAO {

    List<Route> getRoutesByRouteNumberAndTransportType(Integer routeNumber, TransportType transportType);
}
