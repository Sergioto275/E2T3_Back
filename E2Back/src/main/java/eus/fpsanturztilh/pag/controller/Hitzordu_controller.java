package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/hitzorduak")
public class Hitzordu_controller {

	@Autowired
	Hitzordu_ServiceImpl hitzorduService; 
	
    @GetMapping("")
    public ResponseEntity<List<Hitzorduak>> getAllHitzorduak() {
    	
        List<Hitzorduak> hitzorduakList = hitzorduService.getAll();
        return ResponseEntity.ok(hitzorduakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Hitzorduak> findHitzorduak(@PathVariable Long id) {
    	Optional<Hitzorduak> hitzordua_list = hitzorduService.find(id);
    	if(hitzordua_list.isPresent()) {
    		return ResponseEntity.ok(hitzordua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Hitzorduak> createHitzorduak(@RequestBody Hitzorduak hitzordu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hitzorduService.create(hitzordu));
	}
}

