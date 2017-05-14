package transportproject.transportwebsite.service;

import transportproject.transportwebsite.model.transport.Stop;

import java.util.List;
import java.util.Map;

public interface StopService {
    Map<Character, List<Stop>> getSortedStops();
}
