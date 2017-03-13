package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import transportproject.transportwebsite.dao.TransportDAO;

@Controller
public class TransportRoutesController {
    private final TransportDAO transportDAO;

    @Autowired
    public TransportRoutesController(TransportDAO transportDAO) {
        this.transportDAO = transportDAO;
    }


}
