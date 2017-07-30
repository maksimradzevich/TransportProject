package transportproject.transportwebsite.business.stop;

import transportproject.transportwebsite.dto.StopDTO;

import java.util.Iterator;

public interface Stops {
    Iterable<? extends Stop> getStops();
}
