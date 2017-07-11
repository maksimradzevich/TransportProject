package transportproject.transportwebsite.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import transportproject.transportwebsite.model.transport.Route;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RouteDAO extends CrudRepository<Route, Integer>{
    @Query("select r from Route r where r.transport.transportType  = :transportType and r.transport.routeNumber = :routeNumber")
    List<Route> getRoutesByRouteNumberAndTransportType(@Param("routeNumber") Integer routeNumber, @Param("transportType") TransportType transportType);
}
