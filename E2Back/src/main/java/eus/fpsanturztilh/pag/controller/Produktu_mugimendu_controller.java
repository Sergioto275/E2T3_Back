package eus.fpsanturztilh.pag.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.fpsanturztilh.pag.model.Produktu_mugimenduak;
import eus.fpsanturztilh.pag.model.Produktuak;
import eus.fpsanturztilh.pag.service.Langile_service;
import eus.fpsanturztilh.pag.model.Langileak;
import eus.fpsanturztilh.pag.service.Produktu_mugimenduServiceImpl;
import eus.fpsanturztilh.pag.service.Produktu_service;

@RestController
@RequestMapping("/api/produktu_mugimenduak")
public class Produktu_mugimendu_controller {

	@Autowired
	Produktu_mugimenduServiceImpl mugimenduService;
	
	@Autowired
	Produktu_service produktuService;
	
	@Autowired
	Langile_service langileService; 
	
    @GetMapping("")
    public ResponseEntity<List<Produktu_mugimenduak>> getAllMugimenduak() {
    	
        List<Produktu_mugimenduak> mugimenduakList = mugimenduService.getAll();
        return ResponseEntity.ok(mugimenduakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Produktu_mugimenduak> findMugimenduak(@PathVariable Long id) {
    	Optional<Produktu_mugimenduak> mugimendua_list = mugimenduService.find(id);
    	if(mugimendua_list.isPresent()) {
    		return ResponseEntity.ok(mugimendua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
	public ResponseEntity<Produktu_mugimenduak> createMugimenduak(@RequestBody Produktu_mugimenduak mugimenduak) {
    	Long id_produktu = mugimenduak.getProduktu().getId();
    	Optional<Produktuak> produktuak_list = produktuService.find(id_produktu);
    	
    	Long id_langile = mugimenduak.getLangile().getId();
    	Optional<Langileak> langileak_list = langileService.find(id_langile);
    	
    	if(produktuak_list.isPresent() && langileak_list.isPresent()) {
    		mugimenduak.setProduktu(produktuak_list.get());
    		mugimenduak.setLangile(langileak_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(mugimenduService.create(mugimenduak));
	}
}
