package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportproject.transportwebsite.business.user.UserImpl;
import transportproject.transportwebsite.business.user.UserWithFavorites;
import transportproject.transportwebsite.business.user.UserWithFavoritesImpl;
import transportproject.transportwebsite.dao.StopDTODAO;
import transportproject.transportwebsite.dao.TransportDTODAO;
import transportproject.transportwebsite.dao.UserDTODAO;
import transportproject.transportwebsite.dto.FavoriteItem;
import transportproject.transportwebsite.service.UserService;
import transportproject.transportwebsite.service.UserWithFavoritesService;
import transportproject.transportwebsite.service.exceptions.NotFoundException;

@RestController
public class FavoriteController {

    private final UserWithFavoritesService userService;

    @Autowired
    public FavoriteController(UserWithFavoritesService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> favoriteItem(@RequestBody FavoriteItem item) {
        try {
           userService.findActiveUser().addFavorite(item);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/unfavorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> unfavoriteItem(@RequestBody FavoriteItem item) {
        try {
            userService.findActiveUser().removeFavorite(item);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
