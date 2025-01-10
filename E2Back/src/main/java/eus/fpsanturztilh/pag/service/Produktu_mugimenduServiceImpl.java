package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.Produktu_mugimenduak;
import eus.fpsanturztilh.pag.repository.Produktu_mugimenduak_repository;

@Service
public class Produktu_mugimenduServiceImpl implements Produktu_mugimendua_service{

	@Autowired
	Produktu_mugimenduak_repository mugimenduakRepository; 
	
	@Override
	public List<Produktu_mugimenduak> getAll()
	{
		List<Produktu_mugimenduak> mugimenduList = mugimenduakRepository.findAll();
        return mugimenduList;
	}
	
	@Override
    public Optional<Produktu_mugimenduak> find(Long id)
    {
    	Optional<Produktu_mugimenduak> mugimenduList = mugimenduakRepository.findById(id);
    	return mugimenduList;
    }
	
	@Override
    public Produktu_mugimenduak create(Produktu_mugimenduak mugimenduak)
    {
		
		return mugimenduakRepository.save(mugimenduak);
    }
}
