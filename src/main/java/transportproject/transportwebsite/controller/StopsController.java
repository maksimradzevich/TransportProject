package transportproject.transportwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StopsController {

    private static final String STOPS_TEMPLATE = "stops";
    private static final String STOPS_MAPPING = "/stops";

    /**
     * stops page controller method
     *
     * @return stops template
     */
    @GetMapping(STOPS_MAPPING)
    public String stopsPage() {
        return STOPS_TEMPLATE;
    }
}
