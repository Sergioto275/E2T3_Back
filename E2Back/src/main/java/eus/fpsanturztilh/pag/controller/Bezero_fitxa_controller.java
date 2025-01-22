package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/bezero_fitxak")
public class Bezero_fitxa_controller {

	@Autowired
	Bezero_fitxak_ServiceImpl bezeroFitxaService_historiala; 
	
    @GetMapping("")
    public ResponseEntity<List<Bezero_fitxak>> getAllBezeroFitxaHistoriala() {
    	
        List<Bezero_fitxak> bezeroFitxaHistorialaList = bezeroFitxaService_historiala.getAll();
        return ResponseEntity.ok(bezeroFitxaHistorialaList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Bezero_fitxak> findBezeroFitxaHistoriala(@PathVariable Long id) {
    	Optional<Bezero_fitxak> bezeroFitxaHistorialaList = bezeroFitxaService_historiala.find(id);
    	if(bezeroFitxaHistorialaList.isPresent()) {
    		return ResponseEntity.ok(bezeroFitxaHistorialaList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Bezero_fitxak> createKoloreHistoriala(@RequestBody Bezero_fitxak bezeroFitxaHistoriala) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bezeroFitxaService_historiala.save(bezeroFitxaHistoriala));
	}
}

