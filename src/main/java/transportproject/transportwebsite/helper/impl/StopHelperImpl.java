package transportproject.transportwebsite.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.helper.StopHelper;
import transportproject.transportwebsite.model.Stop;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ImplicitNumericConversion")
@Service("stopHelper")
public class StopHelperImpl implements StopHelper {

    private final StopDAO stopDAO;

    @Autowired
    public StopHelperImpl(StopDAO stopDAO) {
        this.stopDAO = stopDAO;
    }

    @Override
    public Map<Character, List<Stop>> getSortedStops() {
        final List<Stop> allStops = stopDAO.findAllStopsSorted();
        final Map<Character, List<Stop>> mapOfSortedStops = sortStops(allStops);

        return mapOfSortedStops;
    }

    static Map<Character, List<Stop>> sortStops(List<Stop> stops) {

        final Map<Character, List<Stop>> stopsMap = new TreeMap<>(Character::compareTo);

        char temporaryLetter = ' ';
        List<Stop> temporaryList = null;

        for (Stop st : stops) {

            final char firstLetterOfName = st.getName().toUpperCase().charAt(0);

            if (temporaryLetter == firstLetterOfName) {
                temporaryList.add(st);
            } else if (stopsMap.containsKey(firstLetterOfName)) {
                temporaryLetter = firstLetterOfName;
                temporaryList = stopsMap.get(temporaryLetter);
                temporaryList.add(st);
            } else {
                final List<Stop> list = new ArrayList<>(0);
                list.add(st);
                stopsMap.put(firstLetterOfName, list);
            }
        }

        return stopsMap;
    }
}
