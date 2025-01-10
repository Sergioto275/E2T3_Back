package eus.fpsanturztilh.pag.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.repository.*;

@Service
public class Material_mailegu_ServiceImpl implements Material_mailegu_service {
	@Autowired
	Material_mailegua_repository maileguaRepository; 
	
	@Override
	public List<Material_mailegua> getAll()
	{
		List<Material_mailegua> mailegua_list = maileguaRepository.findAll();
        return mailegua_list;
	}
	
	@Override
    public Optional<Material_mailegua> find(Long id)
    {
    	Optional<Material_mailegua> mailegua_list = maileguaRepository.findById(id);
    	return mailegua_list;
    }
	
	@Override
    public Material_mailegua create(Material_mailegua material_mailegua)
    {
		return maileguaRepository.save(material_mailegua);
    }
}
