package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/ordutegiak")
@Tag(name = "Ordutegiak", description = "Ordutegiak kudeatzeko kontroladorea")
public class Ordutegi_controller {

	@Autowired
	Ordutegi_ServiceImpl ordutegiService;

	@Autowired
	Talde_service taldeService;

	// Obtener todos los horarios
	@GetMapping("")
	@Operation(summary = "Ordutegi guztiak lortzea", description = "Ordutegi guztiak itzultzen ditu. Ez badira aurkitzen, 204 No Content itzultzen du.", responses = {
			@ApiResponse(responseCode = "200", description = "Eragiketa arrakastatsua", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "204", description = "Ez dago ordutegirik") })

	public ResponseEntity<List<Ordutegiak>> getAllOrdutegiak() {
		List<Ordutegiak> ordutegiList = ordutegiService.getAll();
		if (ordutegiList.isEmpty()) {
			return ResponseEntity.noContent().build(); // Si no hay horarios, devolver 204 No Content
		}
		return ResponseEntity.ok(ordutegiList); // Si hay horarios, devolverlos con código 200 OK
	}

	// Obtener un horario por ID
	@GetMapping("/id/{id}")
	@Operation(summary = "Ordutegi bat lortzea IDaren arabera", description = "Ordutegi bat bilatzen du IDarekin eta aurkitzen bada itzultzen du.", responses = {
			@ApiResponse(responseCode = "200", description = "Ordutegia aurkitu da", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Ordutegia ez da aurkitu") })
	public ResponseEntity<Ordutegiak> getOrdutegiById(@PathVariable Long id) {
		Optional<Ordutegiak> ordutegi = ordutegiService.find(id);
		if (ordutegi.isPresent()) {
			return ResponseEntity.ok(ordutegi.get()); // Si lo encontramos, devolver el horario
		}
		return ResponseEntity.notFound().build(); // Si no se encuentra, devolver 404 Not Found
	}

	// Método adicional para actualizar un horario si fuera necesario
	@PutMapping("/id/{id}")
	@Operation(summary = "Ordutegi bat eguneratzea IDaren arabera", description = "Ordutegi bat eguneratzen du. Aurkitzen bada, eguneratu eta itzuli.", responses = {
			@ApiResponse(responseCode = "200", description = "Ordutegia eguneratu da", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Ordutegia ez da aurkitu") })
	public ResponseEntity<Ordutegiak> updateOrdutegi(@PathVariable Long id, @RequestBody Ordutegiak ordutegi) {
		Optional<Ordutegiak> existingOrdutegi = ordutegiService.find(id);

		if (existingOrdutegi.isPresent()) {
			// Actualizar los campos del horario
			ordutegi.setId(id); // Asegurarse de mantener el ID para la actualización
			Ordutegiak updatedOrdutegi = ordutegiService.save(ordutegi);
			return ResponseEntity.ok(updatedOrdutegi); // Devolver el horario actualizado
		}

		return ResponseEntity.notFound().build(); // Si no se encuentra el horario, devolver 404 Not Found
	}

	@PostMapping("")
	@Operation(summary = "Ordutegi berri bat sortzea", description = "Ordutegi berri bat sortzen du. Sartutako datuak baliozkotzen ditu.", responses = {
			@ApiResponse(responseCode = "201", description = "Ordutegia sortu da", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Sartutako datuak ez dira baliodunak"),
			@ApiResponse(responseCode = "500", description = "Barruko zerbitzu errorea") })
	public ResponseEntity<?> createOrdutegiak(@RequestBody Ordutegiak ordutegiak) {
		try {
			if (ordutegiak == null || ordutegiak.hasInvalidFields()) {
				return new ResponseEntity<>("Datos de entrada no válidos", HttpStatus.BAD_REQUEST);
			}

			Ordutegiak createdOrdutegiak = ordutegiService.save(ordutegiak);
			return new ResponseEntity<>(createdOrdutegiak, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Valores incorrectos en la petición", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Eliminar un horario por ID
	@DeleteMapping("/id/{id}")
	@Operation(summary = "Ordutegi bat ezabatzea", description = "Ordutegi bat ezabatzen du IDaren arabera. Horretarako ezabatze data eguneratzen da.", responses = {
			@ApiResponse(responseCode = "200", description = "Ordutegia arrakastaz ezabatuta", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Ordutegia ez da aurkitu") })
	public ResponseEntity<String> deleteOrdutegi(@PathVariable Long id) {
		Optional<Ordutegiak> ordutegi = ordutegiService.find(id);

		if (ordutegi.isPresent()) {
			Ordutegiak horario = ordutegi.get();
			// Puedes agregar la lógica de establecer la fecha de eliminación aquí
			// Por ejemplo, si tu entidad tiene un campo 'ezabatzeData', lo puedes
			// actualizar:
			horario.setEzabatzeData(LocalDateTime.now()); // Establece la fecha y hora actuales
			ordutegiService.save(horario); // Guardamos el horario con la fecha de eliminación

			// O si prefieres eliminarlo completamente:
			ordutegiService.save(horario); // Llamamos al método de eliminación en el servicio
			return ResponseEntity.ok("Horario eliminado correctamente"); // Retornar un mensaje de éxito
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado"); // Si no existe el horario
	}

}
