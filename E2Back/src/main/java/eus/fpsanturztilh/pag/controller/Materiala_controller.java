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

import eus.fpsanturztilh.pag.model.Materialak;
import eus.fpsanturztilh.pag.service.MaterialaServiceImpl;

@RestController
@RequestMapping("/api/materialak")
public class Materiala_controller {

	@Autowired
	MaterialaServiceImpl materialaService;
	
	//@Autowired
	//Material_kategoria_service materialKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Materialak>> getAllMaterialak() {
    	
        List<Materialak> materialakList = materialaService.getAll();
        return ResponseEntity.ok(materialakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Materialak> findMateriala(@PathVariable Long id) {
    	Optional<Materialak> materiala_list = materialaService.find(id);
    	if(materiala_list.isPresent()) {
    		return ResponseEntity.ok(materiala_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    /*@PostMapping("")
    public ResponseEntity<Materialak> createMateriala(@RequestBody Materialak materiala) {
    	String id = materiala.getMaterial_kategoria().getId();
    	Optional<Material_kategoria> kategoria_list = materialKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		materiala.setMaterial_kategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(materialaService.create(materiala));
	}*/
}

