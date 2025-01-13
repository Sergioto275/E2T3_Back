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

import eus.fpsanturztilh.pag.model.Produktu_Kategoria;
import eus.fpsanturztilh.pag.model.Produktuak;
import eus.fpsanturztilh.pag.service.ProduktuKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.ProduktuServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/produktu_kategoria")
public class Produktu_kategoria_controller {

	@Autowired
	ProduktuKategoriaServiceImpl produktuKatService;
	
	//@Autowired
	//Produktu_kategoria_service produktuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Produktu_Kategoria>> getAllProduktuak() {
    	
        List<Produktu_Kategoria> produktuakKatList = produktuKatService.getAll();
        return ResponseEntity.ok(produktuakKatList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Produktu_Kategoria> findProduktu(@PathVariable Long id) {
    	Optional<Produktu_Kategoria> produktuakKatList = produktuKatService.find(id);
    	if(produktuakKatList.isPresent()) {
    		return ResponseEntity.ok(produktuakKatList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
	public ResponseEntity<Produktu_Kategoria> createProduktua(@RequestBody Produktu_Kategoria produktu) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(produktuKatService.create(produktu));
	}
}

