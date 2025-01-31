package eus.fpsanturztilh.pag.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.fpsanturztilh.pag.model.Zerbitzu_kategoria;
import eus.fpsanturztilh.pag.model.Zerbitzuak;
import eus.fpsanturztilh.pag.service.ZerbitzuKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.ZerbitzuServiceImpl;


@RestController
@RequestMapping("/api/zerbitzu_kategoria")
@CrossOrigin(origins = "http://localhost:8100")
public class Zerbitzu_kategoria_controller {

	@Autowired
	ZerbitzuKategoriaServiceImpl zerbitzuKategoriaService;
	
	//@Autowired
	//Zerbitzu_kategoria_service zerbitzuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Zerbitzu_kategoria>> getAllZerbitzuak() {
    	
        List<Zerbitzu_kategoria> zerbitzuKategoriaList = zerbitzuKategoriaService.getAll();
        return ResponseEntity.ok(zerbitzuKategoriaList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Zerbitzu_kategoria> findZerbitzuak(@PathVariable Long id) {
    	Optional<Zerbitzu_kategoria> zerbitzuKategoriaList = zerbitzuKategoriaService.find(id);
    	if(zerbitzuKategoriaList.isPresent()) {
    		return ResponseEntity.ok(zerbitzuKategoriaList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    /*@PostMapping("")
	public ResponseEntity<Zerbitzuak> createZerbitzua(@RequestBody Zerbitzuak zerbitzu) {
    	String id = zerbitzu.getZerbitzu_kategoria().getId();
    	Optional<Zerbitzu_Kategoria> kategoria_list = zerbitzuKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		zerbitzu.setZerbitzu_kategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(zerbitzuService.create(zerbitzu));
	}*/
}

