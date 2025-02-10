package eus.fpsanturztilh.pag.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.repository.*;

@Service
public class Erabiltzaile_ServiceImpl implements Erabiltzaile_service {
    
    @Autowired
    private Erabiltzaile_repository erabiltzaileRepository;

    // Utilizamos SHA-256 para hacer el hash
    public String hashPassword(String rawPassword) throws NoSuchAlgorithmException {
    	System.out.println(rawPassword);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8)); // ✅ Forzar UTF-8
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public Optional<Erabiltzaile> findByUsername(String username) {
        return erabiltzaileRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String rawPassword) {
        try {
            Optional<Erabiltzaile> erabiltzaile = erabiltzaileRepository.findByUsername(username);
            System.out.println(rawPassword);
            if (erabiltzaile.isPresent()) {
                String hashedInput = hashPassword(rawPassword);
                String storedHash = erabiltzaile.get().getPasahitza();

                System.out.println("Hashed input password: " + hashedInput);
                System.out.println("Stored password hash: " + storedHash);

                return hashedInput.equals(storedHash);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public Erabiltzaile saveUser(Erabiltzaile erabiltzaile) {
        try {
            // Aquí deberías hashear la contraseña antes de guardarla
            String hashedPassword = hashPassword(erabiltzaile.getPasahitza());
            erabiltzaile.setPasahitza(hashedPassword);
            return erabiltzaileRepository.save(erabiltzaile);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al hashear la contraseña");
        }
    }

}
