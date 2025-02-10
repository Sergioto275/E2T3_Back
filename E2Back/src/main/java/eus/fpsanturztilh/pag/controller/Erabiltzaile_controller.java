package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Erabiltzaile;
import eus.fpsanturztilh.pag.service.Erabiltzaile_ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")  // Permite solicitudes desde Ionic
@RequestMapping("/api/erabiltzaileak")
public class Erabiltzaile_controller {
    @Autowired
    private Erabiltzaile_ServiceImpl erabiltzaileService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("pasahitza");

        // Verificamos la autenticación usando el hash
        if (erabiltzaileService.authenticate(username, password)) {
            Optional<Erabiltzaile> erabiltzaile = erabiltzaileService.findByUsername(username);
            if (erabiltzaile.isPresent()) {
                return ResponseEntity.ok(Map.of("username", username, "rola", erabiltzaile.get().getRola()));
            }
        }
        return ResponseEntity.status(401).body("Autentifikazioa huts egin du");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("pasahitza");  // Cambié a "pasahitza" para hacer match con tu JSON
        String role = credentials.get("rola"); // Puede ser 'user', 'admin', etc.

        if (username == null || password == null || role == null) {
            return ResponseEntity.status(400).body("Faltan datos para registrar el usuario");
        }

        // Verificamos si el usuario ya existe
        Optional<Erabiltzaile> existingUser = erabiltzaileService.findByUsername(username);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body("El usuario ya existe");
        }

        // Creamos un nuevo usuario con la contraseña hasheada
        Erabiltzaile newUser = new Erabiltzaile();
        try {
            // Hasheamos la contraseña antes de guardarla
            String hashedPassword = erabiltzaileService.hashPassword(password);
            newUser.setUsername(username);
            newUser.setPasahitza(hashedPassword);
            newUser.setRola(role);

            // Guardamos el nuevo usuario en la base de datos
            erabiltzaileService.saveUser(newUser);

            return ResponseEntity.status(201).body("Usuario registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
