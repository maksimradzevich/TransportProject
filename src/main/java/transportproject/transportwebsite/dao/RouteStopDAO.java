package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.dto.RouteStop;
import transportproject.transportwebsite.dto.TransportType;

import java.util.List;

public interface RouteStopDAO {
    public List<RouteStop> getRouteStopsByTransportTypeAndRouteNumber(TransportType transportType, Integer routeNumber);

    RouteStop getRouteStopByRouteIdAndStopId(Integer routeId, Integer stopId);

    List<RouteStop> getRouteStopByRouteId(Integer routeId);

    List<RouteStop> getRouteStopsByStopId(Integer stopId);
}
