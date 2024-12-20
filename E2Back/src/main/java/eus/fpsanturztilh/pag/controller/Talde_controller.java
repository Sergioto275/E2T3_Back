package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Taldeak;
import eus.fpsanturztilh.pag.service.TaldeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taldeak")
public class Talde_controller {

	@Autowired
	TaldeServiceImpl taldeService; 
	
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

