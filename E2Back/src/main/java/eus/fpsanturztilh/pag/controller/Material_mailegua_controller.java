package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/material_mailegua")
public class Material_mailegua_controller {

	@Autowired
	Material_mailegu_ServiceImpl mailegua_Service; 
	
    @GetMapping("")
    public ResponseEntity<List<Material_mailegua>> getAllMaileguak() {
    	
        List<Material_mailegua> mailegua_list = mailegua_Service.getAll();
        return ResponseEntity.ok(mailegua_list);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Material_mailegua> findMaileguak(@PathVariable Long id) {
    	Optional<Material_mailegua> mailegua_list = mailegua_Service.find(id);
    	if(mailegua_list.isPresent()) {
    		return ResponseEntity.ok(mailegua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Material_mailegua> createMaileguak(@RequestBody Material_mailegua material_mailegua) {
		return ResponseEntity.status(HttpStatus.CREATED).body(mailegua_Service.create(material_mailegua));
	}
}

