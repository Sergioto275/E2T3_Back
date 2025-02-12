package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Txandak;
import eus.fpsanturztilh.pag.service.Txandak_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/txandak")
@Tag(name = "Txandak", description = "Txandak kudeatzeko kontroladorea")
public class Txandak_controller {

	@Autowired
	private Txandak_service txandakService;

	@GetMapping
	@Operation(summary = "Lortu txanda guztiak", description = "Txanda guztien zerrenda itzultzen du.", responses = {
			@ApiResponse(responseCode = "200", description = "Txandak eskuratu dira"),
			@ApiResponse(responseCode = "204", description = "Ez dago txandarik eskuragarri") })
	public ResponseEntity<List<Txandak>> getAllTxandak() {
		List<Txandak> txandakList = txandakService.getAll();
		if (txandakList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(txandakList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Bilatu txanda IDaren arabera", description = "ID erabiliz txanda bat bilatzen du.", responses = {
			@ApiResponse(responseCode = "200", description = "Txanda aurkitu da"),
			@ApiResponse(responseCode = "404", description = "Txanda ez da aurkitu") })
	public ResponseEntity<Txandak> getTxandakById(@PathVariable Long id) {
		Optional<Txandak> txandakOptional = txandakService.find(id);
		return txandakOptional.map(txandak -> new ResponseEntity<>(txandak, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Operation(summary = "Sortu txanda berria", description = "Txanda berria sortzen du.", responses = {
			@ApiResponse(responseCode = "201", description = "Txanda sortu da"),
			@ApiResponse(responseCode = "500", description = "Errore interna bat gertatu da") })
	public ResponseEntity<Txandak> createTxandak(@RequestBody Txandak txandak) {
		try {
			Txandak createdTxandak = txandakService.create(txandak);
			return new ResponseEntity<>(createdTxandak, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Eguneratu txanda", description = "Dagoen txanda bat eguneratzen du ID erabiliz.", responses = {
			@ApiResponse(responseCode = "200", description = "Txanda eguneratu da"),
			@ApiResponse(responseCode = "404", description = "Txanda ez da aurkitu") })
	public ResponseEntity<Txandak> updateTxandak(@PathVariable Long id, @RequestBody Txandak txandak) {
		Optional<Txandak> txandakOptional = txandakService.find(id);
		if (txandakOptional.isPresent()) {
			txandak.setId(id);
			Txandak updatedTxandak = txandakService.update(txandak);
			return new ResponseEntity<>(updatedTxandak, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Ezabatu txanda", description = "Txanda bat ezabatzen du ID erabiliz (logika biguna: ezabatzeData ezartzen da).", responses = {
			@ApiResponse(responseCode = "200", description = "Txanda ezabatu da"),
			@ApiResponse(responseCode = "404", description = "Txanda ez da aurkitu") })
	public ResponseEntity<Txandak> deleteTxandak(@PathVariable Long id) {
		Optional<Txandak> txandakOptional = txandakService.find(id);
		if (txandakOptional.isPresent()) {
			Txandak txandak = txandakOptional.get();
			txandak.setEzabatzeData(LocalDateTime.now());
			Txandak updatedTxandak = txandakService.update(txandak);
			return new ResponseEntity<>(updatedTxandak, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/mota/{mota}")
	@Operation(summary = "Lortu mota baten txandak", description = "Mota zehatz bat duten txandak itzultzen ditu.", responses = {
			@ApiResponse(responseCode = "200", description = "Txandak eskuratu dira"),
			@ApiResponse(responseCode = "204", description = "Ez dago mota horretako txandarik") })
	public ResponseEntity<List<Txandak>> getTxandakByMota(@PathVariable String mota) {
		List<Txandak> txandakList = txandakService.findByMota(mota);
		if (txandakList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(txandakList, HttpStatus.OK);
	}
}
