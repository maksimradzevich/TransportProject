package transportproject.transportwebsite.helper;

import transportproject.transportwebsite.model.Stop;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StopHelper {
    Map<Character, List<Stop>> getSortedStops();
}
