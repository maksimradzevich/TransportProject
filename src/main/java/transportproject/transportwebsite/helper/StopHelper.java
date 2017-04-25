package transportproject.transportwebsite.helper;

import transportproject.transportwebsite.model.transport.Stop;

import java.util.List;
import java.util.Map;

public interface StopHelper {
    Map<Character, List<Stop>> getSortedStops();
}
