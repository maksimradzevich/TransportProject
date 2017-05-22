package transportproject.transportwebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.service.StopService;
import transportproject.transportwebsite.model.transport.Stop;

import java.util.*;

@SuppressWarnings("ImplicitNumericConversion")
@Service("stopHelper")
public class StopServiceImpl implements StopService {

    private final StopDAO stopDAO;
    private final UserDAO userDAO;

    @Autowired
    public StopServiceImpl(StopDAO stopDAO, UserDAO userDAO) {
        this.stopDAO = stopDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Map<Character, List<Stop>> getSortedStops() {
        final List<Stop> allStops = stopDAO.findAllByOrderByNameAsc();
        final Map<Character, List<Stop>> mapOfSortedStops = sortStops(allStops);

        return mapOfSortedStops;
    }

    private static Map<Character, List<Stop>> sortStops(List<Stop> stops) {

        final Map<Character, List<Stop>> stopsMap = new TreeMap<>(Character::compareTo);

        char temporaryLetter = ' ';
        List<Stop> temporaryList = null;

        for (Stop st : stops) {

            final char firstLetterOfName = st.getName().toUpperCase().charAt(0);

            if (temporaryLetter == firstLetterOfName) {
                if (temporaryList != null) {
                    temporaryList.add(st);
                }
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
