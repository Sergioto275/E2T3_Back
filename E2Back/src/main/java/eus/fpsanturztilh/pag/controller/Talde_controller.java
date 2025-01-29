package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde la URL especificada
@RequestMapping("/api/taldeak")
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
    
    
    @PutMapping("/{kodea}")
    public ResponseEntity<Taldeak> saveTaldea(@PathVariable String kodea, @RequestBody Taldeak taldeUpdated) {
        Optional<Taldeak> talde_list = taldeService.find(kodea);
        if (talde_list.isPresent()) {
            Taldeak existingTaldea = talde_list.get();
            
            existingTaldea.setIzena(taldeUpdated.getIzena());
            existingTaldea.setKodea(taldeUpdated.getKodea());
            existingTaldea.setEguneratzeData(LocalDateTime.now());
            
            taldeService.save(existingTaldea); 
            return ResponseEntity.status(HttpStatus.OK).body(existingTaldea);  // Devolver el objeto actualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    
    @DeleteMapping("/kodea/{kodea}")
    public ResponseEntity<Taldeak> deleteTaldea(@PathVariable String kodea) {
    	Optional<Taldeak> talde_list = taldeService.find(kodea);
    	if (talde_list.isPresent()) {
    		Taldeak existingTaldea = talde_list.get();
    		existingTaldea.setEzabatzeData(LocalDateTime.now());
    	    taldeService.save(existingTaldea);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

