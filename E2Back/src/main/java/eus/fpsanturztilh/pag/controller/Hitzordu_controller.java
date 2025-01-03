package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Hitzorduak;
import eus.fpsanturztilh.pag.repository.Hitzordu_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hitzorduak")
public class Hitzordu_controller {

	@Autowired
	Hitzordu_repository hitzorduRepository; 
	
    @GetMapping("")
    public ResponseEntity<List<Hitzorduak>> getAllHitzorduak() {
    	
        List<Hitzorduak> hitzorduakList = hitzorduRepository.findAll();
        return ResponseEntity.ok(hitzorduakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Hitzorduak> findHitzorduak(@PathVariable Long id) {
    	Optional<Hitzorduak> hitzordua_list = hitzorduRepository.findById(id);
    	if(hitzordua_list.isPresent()) {
    		return ResponseEntity.ok(hitzordua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Hitzorduak> createHitzorduak(@RequestBody Hitzorduak hitzordu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hitzorduRepository.save(hitzordu));
	}
}

