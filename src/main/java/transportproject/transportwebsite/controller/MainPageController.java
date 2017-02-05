package transportproject.transportwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private static final String MAIN_PAGE_TEMPLATE = "index";
    private static final String MAIN_PAGE_MAPPING = "/";

    /**
     * index page controller method
     *
     * @return main page template
     */
    @GetMapping(MAIN_PAGE_MAPPING)
    public String mainPage() {
        return MAIN_PAGE_TEMPLATE;
    }
}
