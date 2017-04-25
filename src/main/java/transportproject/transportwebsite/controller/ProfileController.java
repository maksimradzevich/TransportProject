package transportproject.transportwebsite.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured({"ROLE_admin", "ROLE_user"})
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage(Model model) {
        return "profile";
    }
}
