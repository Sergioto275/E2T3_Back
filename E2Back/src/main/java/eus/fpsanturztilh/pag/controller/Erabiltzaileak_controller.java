package eus.fpsanturztilh.pag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import eus.fpsanturztilh.pag.model.Erabiltzaileak;
//import eus.fpsanturztilh.pag.service.ErabiltzaileakService;
import eus.fpsanturztilh.pag.service.Erabiltzaile_Service;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/erabiltzaileak")
public class Erabiltzaileak_controller {

    @Autowired
    private Erabiltzaile_Service erabiltzaileakService;

    @GetMapping
    public List<Erabiltzaileak> getAllUsers() {
        return erabiltzaileakService.getAllUsers();
    }

    @GetMapping("/{erabiltzaileak}")
    public ResponseEntity<Erabiltzaileak> getUserByUsername(@PathVariable String username) {
        Optional<Erabiltzaileak> erabiltzaileak = erabiltzaileakService.getUserByUsername(username);
        return erabiltzaileak.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Erabiltzaileak> createUser(@RequestBody Erabiltzaileak erabiltzaileak) {
        return ResponseEntity.ok(erabiltzaileakService.createUser(erabiltzaileak));
    }

    @PutMapping("/{erabiltzaileak}")
    public ResponseEntity<Erabiltzaileak> updateUser(@PathVariable String username, @RequestBody Erabiltzaileak erabiltzaileak) {
        Erabiltzaileak updatedUser = erabiltzaileakService.updateUser(username, erabiltzaileak);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{erabiltzaileak}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        erabiltzaileakService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
