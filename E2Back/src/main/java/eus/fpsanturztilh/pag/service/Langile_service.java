package eus.fpsanturztilh.pag.service;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import eus.fpsanturztilh.pag.model.Langileak;

public interface Langile_service {
	
	public List<Langileak> getAll();
    
    public Optional<Langileak> find(Long id);
    
    public Langileak create(Langileak langile);
    
}
