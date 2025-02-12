package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Erabiltzaile;
import eus.fpsanturztilh.pag.service.Erabiltzaile_ServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")  // Permite solicitudes desde Ionic
@RequestMapping("/api/erabiltzaileak")
@Tag(name = "Erabiltzailea", description = "Erabiltzaileak kudeatzeko kontroladorea")
public class Erabiltzaile_controller {
    @Autowired
    private Erabiltzaile_ServiceImpl erabiltzaileService;

    @PostMapping("/login")
    
    public ResponseEntity<?> login(@RequestBody Erabiltzaile credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPasahitza();
        if (erabiltzaileService.authenticate(username, password)) {
            Optional<Erabiltzaile> erabiltzaile = erabiltzaileService.findByUsername(username);
            if (erabiltzaile.isPresent()) {
            	System.out.println("good");
                return ResponseEntity.ok(Map.of("username", username, "rola", erabiltzaile.get().getRola(), "status", true));
            }
        }
        return ResponseEntity.status(401).body(Map.of("status", false));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Erabiltzaile credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPasahitza();
        String role = credentials.getRola();

        if (username == null || password == null || role == null) {
            return ResponseEntity.status(400).body("Faltan datos para registrar el usuario");
        }
        Optional<Erabiltzaile> existingUser = erabiltzaileService.findByUsername(username);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body("El usuario ya existe");
        }
        try {
            erabiltzaileService.saveUser(credentials);
            return ResponseEntity.status(201).body("Usuario registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
