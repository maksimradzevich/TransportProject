package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.business.user.UserWithFavoritesImpl;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.FavoriteItem;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

@RestController
public class FavoriteController {

    private final UserService userService;
    private final StopDTODAO stopDTODAO;
    private final TransportDTODAO transportDTODAO;
    private final UserDTODAO userDTODAO;

    @Autowired
    public FavoriteController(UserService userService, StopDTODAO stopDTODAO, TransportDTODAO transportDTODAO, UserDTODAO userDTODAO) {
        this.userService = userService;
        this.stopDTODAO = stopDTODAO;
        this.transportDTODAO = transportDTODAO;
        this.userDTODAO = userDTODAO;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> favoriteItem(@RequestBody FavoriteItem item) {
        try {
            new UserWithFavoritesImpl(
                    userService.findActiveUser(),
                    stopDTODAO,
                    transportDTODAO
            ).addFavorite(item);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/unfavorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> unfavoriteItem(@RequestBody FavoriteItem item) {
        try {
            new UserWithFavoritesImpl(
                    userService.findActiveUser(),
                    stopDTODAO,
                    transportDTODAO
            ).removeFavorite(item);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
