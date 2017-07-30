package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.UserDTO;
import transportproject.transportwebsite.service.exceptions.UserExistsException;

@Controller
public class RegistrationController {
    private final UserDTODAO userDTODAO;

    @Autowired
    public RegistrationController(UserDTODAO userDTODAO) {
        this.userDTODAO = userDTODAO;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(UserDTO userDTO, Model model) {
        try {
            new UserImpl(userDTO, userDTODAO).register();
        } catch (UserExistsException e) {
            model.addAttribute("exception", e);
            return "registration";
        }
        return "redirect:login";
    }
}
