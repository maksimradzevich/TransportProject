package transportproject.transportwebsite.service;

import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.TransportType;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;

public interface RouteService {
    List<Route> getRoutesByNumberAndTransportType(Integer routeNumber, TransportType type) throws NotFoundException;
}
