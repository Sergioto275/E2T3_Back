package eus.fpsanturztilh.pag.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import eus.fpsanturztilh.pag.model.Hitzorduak;

public interface Hitzordu_service {
	public List<Hitzorduak> getAll();
    
    public Hitzorduak find(Long id);
    
    public Hitzorduak create(Hitzorduak hitzordu);
    
}
