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

import eus.fpsanturztilh.pag.model.Produktuak;
import eus.fpsanturztilh.pag.service.ProduktuServiceImpl;

@RestController
@RequestMapping("/api/produktuak")
public class Produktu_controller {

	@Autowired
	ProduktuServiceImpl produktuService;
	
	//@Autowired
	//Produktu_kategoria_service produktuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Produktuak>> getAllProduktuak() {
    	
        List<Produktuak> produktuakList = produktuService.getAll();
        return ResponseEntity.ok(produktuakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Produktuak> findProduktu(@PathVariable Long id) {
    	Optional<Produktuak> produktua_list = produktuService.find(id);
    	if(produktua_list.isPresent()) {
    		return ResponseEntity.ok(produktua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    /*@PostMapping("")
	public ResponseEntity<Produktuak> createProduktua(@RequestBody Produktuak produktu) {
    	String id = produktu.getProduktu_kategoria().getId();
    	Optional<Produktu_kategoria> kategoria_list = produktuKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		produktu.setProduktu_kategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(produktuService.create(produktu));
	}*/
}

