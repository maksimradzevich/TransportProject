package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import transportproject.transportwebsite.dao.UserDAO;
import transportproject.transportwebsite.service.RegistrationService;
import transportproject.transportwebsite.service.exceptions.UserExistsException;
import transportproject.transportwebsite.model.User;

@Controller
public class RegistrationController {
    private final UserDAO userDAO;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(UserDAO userDAO, RegistrationService registrationService) {
        this.userDAO = userDAO;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(User user, Model model) {
        try {
            registrationService.registerUser(user);
        } catch (UserExistsException e) {
            model.addAttribute("exception", e);
            return "registration";
        }
        return "redirect:login";
    }
}
