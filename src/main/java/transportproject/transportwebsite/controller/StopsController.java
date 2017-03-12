package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dao.StopDAO;
import transportproject.transportwebsite.helper.StopHelper;
import transportproject.transportwebsite.model.Stop;

import java.util.List;
import java.util.Map;

@Controller
public class StopsController {

    private static final String STOPS_TEMPLATE = "stops";
    private static final String STOPS_MAPPING = "/stops";


    private final StopHelper stopHelper;

    @Autowired
    public StopsController(StopHelper stopHelper) {
        this.stopHelper = stopHelper;
    }

    /**
     * stops page controller method
     *
     * @return stops template
     */
    @GetMapping(STOPS_MAPPING)
    public String stopsPage(Model model) {
        final Map<Character, List<Stop>> sortedStops = stopHelper.getSortedStops();
        model.addAttribute("stops", sortedStops);
        return STOPS_TEMPLATE;
    }
}
