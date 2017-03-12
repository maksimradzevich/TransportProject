package transportproject.transportwebsite.dao;

import transportproject.transportwebsite.model.Stop;

import java.util.List;

public interface StopDAO {
    List<Stop> findAllStops();

    List<Stop> findAllStopsSorted();
}
