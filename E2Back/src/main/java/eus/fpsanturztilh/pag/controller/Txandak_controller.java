package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Txandak;
import eus.fpsanturztilh.pag.service.Txandak_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/txandak") // Ruta base para este controlador
public class Txandak_controller {

    @Autowired
    private Txandak_service txandakService;

    // Obtener todos los Txandak
    @GetMapping
    public ResponseEntity<List<Txandak>> getAllTxandak() {
        List<Txandak> txandakList = txandakService.getAll();
        if (txandakList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si la lista está vacía
        }
        return new ResponseEntity<>(txandakList, HttpStatus.OK); // 200 OK
    }

    // Obtener un Txandak por ID
    @GetMapping("/{id}")
    public ResponseEntity<Txandak> getTxandakById(@PathVariable Long id) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        if (txandakOptional.isPresent()) {
            return new ResponseEntity<>(txandakOptional.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si no existe
        }
    }

    // Crear un nuevo Txandak
    @PostMapping
    public ResponseEntity<Txandak> createTxandak(@RequestBody Txandak txandak) {
        try {
            Txandak createdTxandak = txandakService.create(txandak);
            return new ResponseEntity<>(createdTxandak, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error en caso de error
        }
    }

    // Actualizar un Txandak
    @PutMapping("/{id}")
    public ResponseEntity<Txandak> updateTxandak(@PathVariable Long id, @RequestBody Txandak txandak) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        if (txandakOptional.isPresent()) {
            txandak.setId(id); // Aseguramos que el ID es el correcto
            Txandak updatedTxandak = txandakService.create(txandak); // Reutilizamos el método create para actualización
            return new ResponseEntity<>(updatedTxandak, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si no existe el ID
        }
    }

    // Eliminar un Txandak
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTxandak(@PathVariable Long id) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        if (txandakOptional.isPresent()) {
            // Si existe, eliminamos el Txandak
            txandakService.create(txandakOptional.get()); // Llamar al servicio para eliminar
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content al eliminar con éxito
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found si no se encuentra
        }
    }

    // Buscar por Mota (si tienes este campo)
    @GetMapping("/mota/{mota}")
    public ResponseEntity<List<Txandak>> getTxandakByMota(@PathVariable String mota) {
        List<Txandak> txandakList = txandakService.findByMota(mota); // o txandakService.findByMota(mota);
        if (txandakList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si no se encuentra
        }
        return new ResponseEntity<>(txandakList, HttpStatus.OK); // 200 OK si se encuentra
    }
}
