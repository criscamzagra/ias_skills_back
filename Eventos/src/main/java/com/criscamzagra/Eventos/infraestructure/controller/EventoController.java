package com.criscamzagra.Eventos.infraestructure.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.criscamzagra.Eventos.application.evento.model.Evento;
import com.criscamzagra.Eventos.application.evento.ports.out.EventoRepository;
import com.criscamzagra.Eventos.application.usuario.model.Usuario;
import com.criscamzagra.Eventos.application.usuario.ports.out.UsuarioRepository;

@RestController
@RequestMapping("/iasapi")
public class EventoController {

	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/events")
	public ResponseEntity<?> getEvents() {
		try {
			List<Evento> listaEventos = eventoRepository.findAll();
			Map<String, Object> response = new HashMap<>();
			response.put("data", listaEventos);
			response.put("status", 202);
			response.put("message", "Respuesta ok");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error(e.getMessage());
			Map<String, Object> response = new HashMap<>();
			response.put("status", 404);
			response.put("message", "error " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/events")
	public ResponseEntity<?> createEvents(@Validated @RequestBody Evento eventoRequest) {
		try {
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(eventoRequest.getUsaurio().getUserId());
			
			if(!usuarioOptional.isPresent()) {
				Map<String, Object> response = new HashMap<>();
				response.put("status", 404);
				response.put("message", "Usuario no registrado en la base de datos ");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	
			}
			
			Evento evento = new Evento();
			
			evento.setDate(eventoRequest.getDate());
			evento.setLocation(eventoRequest.getLocation());
			evento.setName(eventoRequest.getName());
			evento.setUsaurio(usuarioOptional.get());
			
			Evento eventoRegistrdo=eventoRepository.save(evento);
			
			
			Map<String, Object> response = new HashMap<>();
			response.put("data", eventoRegistrdo);
			response.put("status", 202);
			response.put("message", "Respuesta ok");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error(e.getMessage());
			Map<String, Object> response = new HashMap<>();
			response.put("status", 404);
			response.put("message", "error " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<?> getEventsById(@RequestParam Long id) {
		try {
			Optional<Evento> eventoOptional = eventoRepository.findById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("data", eventoOptional);
			response.put("status", 202);
			response.put("message", "Respuesta ok");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error(e.getMessage());
			Map<String, Object> response = new HashMap<>();
			response.put("status", 404);
			response.put("message", "error " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/events/{id}/register")
	public ResponseEntity<?> registerEvents(@RequestParam Long id) {
		try {
			Optional<Evento> eventoOptional = eventoRepository.findById(id);
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
			if(!eventoOptional.isPresent()) {
				Map<String, Object> response = new HashMap<>();
				response.put("status", 404);
				response.put("message", "No exste un evento registrado con ese id ");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	
				
			}
			
			Evento evento = new Evento();
			
			evento.setDate(eventoOptional.get().getDate());
			evento.setLocation(eventoOptional.get().getLocation());
			evento.setName(eventoOptional.get().getName());
			evento.setUsaurio(usuarioOptional.get());
			
			eventoRepository.save(evento);
			
			Map<String, Object> response = new HashMap<>();
			response.put("mensaje", "Respuesta ok");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error(e.getMessage());
			Map<String, Object> response = new HashMap<>();
			response.put("status", 404);
			response.put("message", "error " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	/*
	@GetMapping("/events/user/{userId}")
	public ResponseEntity<?> getEventsByIdUsers(@RequestParam  Long userId){
		try {
			
			List<Evento> listEventos= eventoRepository.findEventsByIdUsers(userId);
			Map<String, Object> response = new HashMap<>();
			response.put("data", listEventos);
			response.put("status", 202);
			response.put("message", "Respuesta ok");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error(e.getMessage());
			Map<String, Object> response = new HashMap<>();
			response.put("status", 404);
			response.put("message", "error " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
*/

}
