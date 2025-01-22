package eus.fpsanturztilh.pag.service;

import java.util.List;
import java.util.Optional;

import eus.fpsanturztilh.pag.model.Txandak;

public interface Txandak_service {
public List<Txandak> getAll();
    
    public Optional<Txandak> find(Long id);
    
    public Txandak create(Txandak txandak);
    
    public List<Txandak> findByMota(String mota);
 
}