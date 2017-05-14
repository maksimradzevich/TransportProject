package transportproject.transportwebsite.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public String errorPage(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
