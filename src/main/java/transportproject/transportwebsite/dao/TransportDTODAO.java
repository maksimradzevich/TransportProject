package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.dto.TransportDTO;
import transportproject.transportwebsite.dto.TransportType;

import java.util.List;

public interface TransportDTODAO {
    List<TransportDTO> findAllTransport();

    List<TransportDTO> findTransportByType(TransportType transportType);

    TransportDTO findTransportByRouteNumberAndTypeWithRoutes(int routeNumber, TransportType transportType);
    TransportDTO findTransportByRouteNumberAndType(int routeNumber, TransportType transportType);

    TransportDTO findById(int id);
}
