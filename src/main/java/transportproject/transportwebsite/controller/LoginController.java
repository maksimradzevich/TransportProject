package transportproject.transportwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import transportproject.transportwebsite.dto.UserDTO;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(UserDTO userDTO, Model model) {
        return "loginPage";
    }
}
