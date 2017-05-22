package transportproject.transportwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;

@RestController
public class FavoriteController {
    @PostMapping("favorite/{type}/{id}")
    public ResponseEntity<Void> favoriteItem(@PathVariable("type") String type, @PathVariable("id") String id) {

    }
}
