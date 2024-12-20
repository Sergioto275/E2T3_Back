package eus.fpsanturztilh.pag.service;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import eus.fpsanturztilh.pag.model.Taldeak;

public interface Talde_service {
	
	public List<Taldeak> getAll();
    
    public Optional<Taldeak> find(String kodea);
    
    public Taldeak create(Taldeak taldea);
    
}
