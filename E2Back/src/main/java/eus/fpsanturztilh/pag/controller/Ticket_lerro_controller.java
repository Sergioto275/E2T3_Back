package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/ticket_lerroak")
public class Ticket_lerro_controller {

	@Autowired
	Ticket_lerro_ServiceImpl ticket_lerroaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Ticket_lerroa>> getAllTicketLerroa() {
    	
        List<Ticket_lerroa> ticket_lerroaList = ticket_lerroaService.getAll();
        return ResponseEntity.ok(ticket_lerroaList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Ticket_lerroa> findTicketLerroa(@PathVariable Long id) {
    	Optional<Ticket_lerroa> ticket_lerroaList = ticket_lerroaService.find(id);
    	if(ticket_lerroaList.isPresent()) {
    		return ResponseEntity.ok(ticket_lerroaList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Ticket_lerroa> createTicketLerroa(@RequestBody Ticket_lerroa hitzordu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ticket_lerroaService.create(hitzordu));
	}
}

