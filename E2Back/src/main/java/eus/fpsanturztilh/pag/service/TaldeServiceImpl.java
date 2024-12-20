package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.Taldeak;
import eus.fpsanturztilh.pag.repository.Talde_repository;

@Service
public class TaldeServiceImpl implements Talde_service {
	@Autowired
	Talde_repository taldeRepository; 
	
	@Override
	public List<Taldeak> getAll()
	{
		List<Taldeak> taldeList = taldeRepository.findAll();
        return taldeList;
	}
	
	@Override
    public Optional<Taldeak> find(String kodea)
    {
    	Optional<Taldeak> talde_list = taldeRepository.findById(kodea);
    	return talde_list;
    }
	
	@Override
    public Taldeak create(Taldeak taldea)
    {
		
		return taldeRepository.save(taldea);
    }
}
