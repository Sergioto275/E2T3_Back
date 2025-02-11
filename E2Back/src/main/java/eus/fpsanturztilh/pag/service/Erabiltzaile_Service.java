package eus.fpsanturztilh.pag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eus.fpsanturztilh.pag.model.Erabiltzaileak;
import eus.fpsanturztilh.pag.repository.Erabiltzaile_repository;

import java.util.List;
import java.util.Optional;

@Service
public class Erabiltzaile_Service {

    @Autowired
    private Erabiltzaile_repository erabiltzaileakRepository;

    public List<Erabiltzaileak> getAllUsers() {
        return erabiltzaileakRepository.findAll();
    }

    public Optional<Erabiltzaileak> getUserByUsername(String username) {
        return erabiltzaileakRepository.findById(username);
    }

    public Erabiltzaileak createUser(Erabiltzaileak erabiltzaileak) {
        return erabiltzaileakRepository.save(erabiltzaileak);
    }

    public Erabiltzaileak updateUser(String username, Erabiltzaileak erabiltzaileak) {
        if (erabiltzaileakRepository.existsById(username)) {
            return erabiltzaileakRepository.save(erabiltzaileak);
        }
        return null;
    }

    public void deleteUser(String username) {
        erabiltzaileakRepository.deleteById(username);
    }
}
