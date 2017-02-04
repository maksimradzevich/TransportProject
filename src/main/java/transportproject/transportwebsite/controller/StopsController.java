package transportproject.transportwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StopsController {

    private static final String STOPS_PAGE = "stops";

    @GetMapping("/stops")
    public String stopsPage() {
        return STOPS_PAGE;
    }
}
