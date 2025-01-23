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

import eus.fpsanturztilh.pag.model.Zerbitzuak;
import eus.fpsanturztilh.pag.service.ZerbitzuServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/zerbitzuak")
public class Zerbitzu_controller {

	@Autowired
	ZerbitzuServiceImpl zerbitzuService;
	
	//@Autowired
	//Zerbitzu_kategoria_service zerbitzuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Zerbitzuak>> getAllZerbitzuak() {
    	
        List<Zerbitzuak> zerbitzuakList = zerbitzuService.getAll();
        return ResponseEntity.ok(zerbitzuakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Zerbitzuak> findZerbitzuak(@PathVariable Long id) {
    	Optional<Zerbitzuak> zerbitzua_list = zerbitzuService.find(id);
    	if(zerbitzua_list.isPresent()) {
    		return ResponseEntity.ok(zerbitzua_list.get());
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

