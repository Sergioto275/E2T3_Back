package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/langileak")
public class Langile_controller {

	@Autowired
	LangileServiceImpl langileService;
	
	@Autowired
	Talde_service taldeService; 
	
    @GetMapping("")
    public ResponseEntity<List<Langileak>> getAllLangileak() {
    	
        List<Langileak> langileakList = langileService.getAll();
        return ResponseEntity.ok(langileakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Langileak> findLangilea(@PathVariable Long id) {
    	Optional<Langileak> langile_list = langileService.find(id);
    	if(langile_list.isPresent()) {
    		return ResponseEntity.ok(langile_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Langileak> createLangilea(@RequestBody Langileak langile) {
    	String kodea = langile.getTaldea().getKodea();
    	Optional<Taldeak> talde_list = taldeService.find(kodea);
    	if(talde_list.isPresent()) {
    		langile.setTaldea(talde_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(langileService.create(langile));
	}
}

