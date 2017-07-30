package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.RouteDAO;
import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.TransportType;
import transportproject.transportwebsite.service.RouteService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    @Autowired
    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public List<RouteDTO> getRoutesByNumberAndTransportType(Integer routeNumber, TransportType type) throws NotFoundException {

        final List<RouteDTO> routeDTOS = routeDAO.getRoutesByRouteNumberAndTransportType(routeNumber, type);
        if (routeDTOS.isEmpty()) {
            throw new NotFoundException("Routes for route " + routeNumber + " and transport type " + type + " not found");
        }
        return routeDTOS;
    }
}
