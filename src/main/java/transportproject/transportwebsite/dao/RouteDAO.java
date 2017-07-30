package transportproject.transportwebsite.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import transportproject.transportwebsite.dto.RouteDTO;
import transportproject.transportwebsite.dto.TransportType;

import java.util.List;

public interface RouteDAO extends CrudRepository<RouteDTO, Integer>{
    @Query("select r from RouteDTO r where r.transport.transportType  = :transportType and r.transport.routeNumber = :routeNumber")
    List<RouteDTO> getRoutesByRouteNumberAndTransportType(@Param("routeNumber") Integer routeNumber, @Param("transportType") TransportType transportType);
}
