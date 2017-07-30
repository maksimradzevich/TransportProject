package transportproject.transportwebsite.business.stop;

import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dto.StopDTO;

import java.util.Iterator;
import java.util.stream.Collectors;

public class StopsImpl implements Stops {

    private final StopDTODAO stopDTODAO;

    public StopsImpl(StopDTODAO stopDTODAO) {
        this.stopDTODAO = stopDTODAO;
    }

    @Override
    public Iterable<? extends Stop> getStops() {
        return stopDTODAO.findAllByOrderByNameAsc().stream().map(stopDTO -> new Stop(stopDTO)).collect(Collectors.toList());
    }
}
