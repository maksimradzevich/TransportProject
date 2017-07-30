package transportproject.transportwebsite.service;

import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.TransportType;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;

public interface RouteService {
    List<RouteDTO> getRoutesByNumberAndTransportType(Integer routeNumber, TransportType type) throws NotFoundException;
}
