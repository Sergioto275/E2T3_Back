package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/taldeak")
@CrossOrigin(origins = "http://localhost:8100")
public class Talde_controller {

	@Autowired
	Talde_ServiceImpl taldeService; 
	
    @GetMapping("")
    public ResponseEntity<List<Taldeak>> getAllTaldeak() {
    	
        List<Taldeak> taldeList = taldeService.getAll();
        return ResponseEntity.ok(taldeList);
	}
    
    @GetMapping("/kodea/{kodea}")
    public ResponseEntity<Taldeak> findTaldeak(@PathVariable String kodea) {
    	Optional<Taldeak> talde_list = taldeService.find(kodea);
    	if(talde_list.isPresent()) {
    		return ResponseEntity.ok(talde_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Taldeak> createTaldeak(@RequestBody Taldeak talde) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taldeService.create(talde));
	}
}

