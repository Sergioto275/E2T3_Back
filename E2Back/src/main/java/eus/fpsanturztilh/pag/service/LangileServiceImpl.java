package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.Langileak;
import eus.fpsanturztilh.pag.repository.Hitzordu_repository;
import eus.fpsanturztilh.pag.repository.Langile_repository;

@Service
public class LangileServiceImpl implements Langile_service {
	@Autowired
	Langile_repository langileRepository; 
	
	@Override
	public List<Langileak> getAll()
	{
		List<Langileak> langileList = langileRepository.findAll();
        return langileList;
	}
	
	@Override
    public Optional<Langileak> find(Long id)
    {
    	Optional<Langileak> langile_list = langileRepository.findById(id);
    	return langile_list;
    }
	
	@Override
    public Langileak create(Langileak langile)
    {
		
		return langileRepository.save(langile);
    }
}
