package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import eus.fpsanturztilh.pag.model.Hitzorduak;
import eus.fpsanturztilh.pag.repository.Hitzordu_repository;

public class HitzorduService implements Hitzordu_service {
	@Autowired
	Hitzordu_repository hitzorduRepository; 
	
	public List<Hitzorduak> getAll()
	{
		List<Hitzorduak> hitzorduakList = hitzorduRepository.findAll();
        return hitzorduakList;
	}
    
    public Optional<Hitzorduak> find(Long id)
    {
    	Optional<Hitzorduak> hitzordua_list = hitzorduService.findById(id);
    	return hitzordua_list;
    }
    
    public Hitzorduak create(Hitzorduak hitzordu);
}
