package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/ticket_lerroak")
public class Ticket_lerro_controller {

	@Autowired
	Ticket_lerroServiceImpl ticket_lerroaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Ticket_lerroa>> getAllHitzorduak() {
    	
        List<Ticket_lerroa> ticket_lerroaList = ticket_lerroaService.getAll();
        return ResponseEntity.ok(ticket_lerroaList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Ticket_lerroa> findHitzorduak(@PathVariable Long id) {
    	Optional<Ticket_lerroa> ticket_lerroaList = ticket_lerroaService.find(id);
    	if(ticket_lerroaList.isPresent()) {
    		return ResponseEntity.ok(ticket_lerroaList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Ticket_lerroa> createHitzorduak(@RequestBody Ticket_lerroa hitzordu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ticket_lerroaService.create(hitzordu));
	}
}

