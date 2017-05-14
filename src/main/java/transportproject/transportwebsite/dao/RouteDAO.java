package transportproject.transportwebsite.dao;

import org.springframework.data.repository.CrudRepository;
import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RouteDAO{
    List<Route> getRoutesByRouteNumberAndTransportType(Integer routeNumber, TransportType transportType);
}
