package transportproject.transportwebsite.helper.impl;

import org.junit.Before;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.service.impl.StopServiceImpl;

import java.util.*;

public class StopHelperImplTest {

    private List<Stop> stops;
//    private Set<Character> firstLetters;
    private StopServiceImpl stopHelper;

    @Before
    public void setUp() throws Exception {
        stopHelper = new StopServiceImpl(null);
        stops = createStops();
//        firstLetters = createLetters();
    }

//    private Set<Character> createLetters() {
//        return new HashSet<Character>(){
//            {
//                add('B');
//                add('C');
//                add('D');
//                add('E');
//                add('F');
//            }
//        };
//    }

    private static List<Stop> createStops() {
        final List<Stop> stops = new ArrayList<>(0);
        char c = 'F';
        for (int i = 0; i < 5; i++ ) {
            final String s = new String(new char[]{c, c});
            stops.add(new Stop(s));
//            System.out.println(i + " " + s);
            c--;
        }
        stops.add(new Stop("DD"));
        stops.add(new Stop("CC"));
        return stops;
    }

//    @Test
//    public void sortStopsTest() throws Exception {
//        final Map<Character, List<Stop>> expected = new TreeMap<Character, List<Stop>>(){{
//            char c = 'B';
//            for (int i = 0; i < 5; i++ ) {
//                final List<Stop> stops = new ArrayList<>(0);
//                final String s = new String(new char[]{c, c});
//                stops.add(new Stop(s));
////                System.out.println(i + " " + s);
//                put(c, stops);
//                c++;
//            }
//
//            get('D').add(new Stop("DD"));
//            get('C').add(new Stop("CC"));
//        }};
//        System.out.println("STOPS");
//        System.out.println(stops);
//        System.out.println("EXPECTED");
//        System.out.println(expected);
//        System.out.println("ACTUAL");
//        final Map<Character, List<Stop>> actual = StopServiceImpl.sortStops(stops);
//        System.out.println(actual);
//
//        Assert.assertEquals(expected, actual);
//
//    }
}