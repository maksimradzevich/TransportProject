package transportproject.transportwebsite.business.stop;

import java.util.List;
import java.util.Map;

public interface StopsAsSortedMap {
    Map<Character, List<Stop>> get();
}
