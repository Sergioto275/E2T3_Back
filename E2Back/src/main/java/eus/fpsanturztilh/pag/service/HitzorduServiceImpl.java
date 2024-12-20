package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.Hitzorduak;
import eus.fpsanturztilh.pag.repository.Hitzordu_repository;

@Service
public class HitzorduServiceImpl implements Hitzordu_service {
	@Autowired
	Hitzordu_repository hitzorduRepository; 
	
	@Override
	public List<Hitzorduak> getAll()
	{
		List<Hitzorduak> hitzorduakList = hitzorduRepository.findAll();
        return hitzorduakList;
	}
	
	@Override
    public Optional<Hitzorduak> find(Long id)
    {
    	Optional<Hitzorduak> hitzordua_list = hitzorduRepository.findById(id);
    	return hitzordua_list;
    }
	
	@Override
    public Hitzorduak create(Hitzorduak hitzordu)
    {
		return hitzorduRepository.save(hitzordu);
    }
}
