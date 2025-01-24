package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/ordutegiak")
@CrossOrigin(origins = "http://localhost:8100")
public class Ordutegi_controller {

	@Autowired
	Ordutegi_ServiceImpl ordutegiService;
	
	@Autowired
	Talde_service taldeService; 
	
    @GetMapping("")
    public ResponseEntity<List<Ordutegiak>> getAllLangileak() {
    	
        List<Ordutegiak> ordutegiList = ordutegiService.getAll();
        return ResponseEntity.ok(ordutegiList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Ordutegiak> findLangilea(@PathVariable Long id) {
    	Optional<Ordutegiak> ordutegi_list = ordutegiService.find(id);
    	if(ordutegi_list.isPresent()) {
    		return ResponseEntity.ok(ordutegi_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Ordutegiak> createLangilea(@RequestBody Ordutegiak langile) {
    	String kodea = langile.getTaldea().getKodea();
    	Optional<Taldeak> talde_list = taldeService.find(kodea);
    	if(talde_list.isPresent()) {
    		langile.setTaldea(talde_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(ordutegiService.save(langile));
	}
}

