package transportproject.transportwebsite.business.stop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StopsAsSortedMapImpl implements StopsAsSortedMap {

    private final Stops stops;

    public StopsAsSortedMapImpl(Stops stops) {
        this.stops = stops;
    }

    @Override
    public Map<Character, List<Stop>> get() {

        final Map<Character, List<Stop>> stopsMap = new TreeMap<>(Character::compareTo);

        char temporaryLetter = ' ';
        List<Stop> temporaryList = null;

        for (Stop stop : stops.getStops()) {

            final char firstLetterOfName = stop.getInnerState().getName().toUpperCase().charAt(0);

            if (temporaryLetter == firstLetterOfName) {
                if (temporaryList != null) {
                    temporaryList.add(stop);
                } else {
                    final List<Stop> list = new ArrayList<>(0);
                    list.add(stop);
                    stopsMap.put(firstLetterOfName, list);
                }
            } else if (isLetterAlreadyExists(stopsMap, firstLetterOfName)) {
                temporaryLetter = firstLetterOfName;
                temporaryList = stopsMap.get(temporaryLetter);
                temporaryList.add(stop);
            }
        }

        return stopsMap;
    }

    private boolean isLetterAlreadyExists(Map<Character, List<Stop>> stopsMap, char firstLetterOfName) {
        return stopsMap.containsKey(firstLetterOfName);
    }
}
