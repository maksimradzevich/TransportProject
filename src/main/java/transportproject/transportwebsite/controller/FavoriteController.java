package transportproject.transportwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transportproject.transportwebsite.dto.FavoriteItem;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.model.transport.Transport;
import transportproject.transportwebsite.service.FavoriteService;

@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> favoriteItem(@RequestBody FavoriteItem item) {
        if (item.getType().equalsIgnoreCase(Stop.class.getSimpleName())) {
            favoriteService.addFavoriteStop(item.getId());
        } else if (item.getType().equalsIgnoreCase(Transport.class.getSimpleName())) {
            favoriteService.addFavoriteTransport(item.getId());
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/unfavorite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> unfavoriteItem(@RequestBody FavoriteItem item) {
        if (item.getType().equalsIgnoreCase(Stop.class.getSimpleName())) {
            favoriteService.removeFavoriteStop(item.getId());
        } else if (item.getType().equalsIgnoreCase(Transport.class.getSimpleName())) {
            favoriteService.removeFavoriteTransport(item.getId());
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
