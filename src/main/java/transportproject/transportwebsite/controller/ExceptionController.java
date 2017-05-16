package transportproject.transportwebsite.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import transportproject.transportwebsite.service.exceptions.NotFoundException;
import transportproject.transportwebsite.service.exceptions.UserExistsException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {NotFoundException.class, UserExistsException.class, NoHandlerFoundException.class})
    public String errorPage(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
