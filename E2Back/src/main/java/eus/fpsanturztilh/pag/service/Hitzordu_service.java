package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eus.fpsanturztilh.pag.model.Hitzorduak;

public interface Hitzordu_service {
	public ResponseEntity<List<Hitzorduak>> getAllHitzorduak();
    
    public ResponseEntity<Hitzorduak> findHitzorduak(@PathVariable Long id);
    
    public ResponseEntity<Hitzorduak> createHitzorduak(@RequestBody Hitzorduak hitzordu);
    
}
