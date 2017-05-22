package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.model.transport.Transport;
import transportproject.transportwebsite.model.transport.TransportType;

import java.util.List;

public interface TransportDAO {
    List<Transport> findAllTransport();

    List<Transport> findTransportByType(TransportType transportType);

    Transport findTransportByRouteNumberAndTypeWithRoutes(int routeNumber, TransportType transportType);
    Transport findTransportByRouteNumberAndType(int routeNumber, TransportType transportType);

    Transport findById(int id);
}
