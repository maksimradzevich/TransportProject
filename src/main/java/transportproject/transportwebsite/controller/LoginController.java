package transportproject.transportwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import transportproject.transportwebsite.model.User;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(User user, Model model) {
        return "loginPage";
    }
}
