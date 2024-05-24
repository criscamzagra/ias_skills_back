package com.criscamzagra.Eventos.application.evento.ports.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.criscamzagra.Eventos.application.evento.model.Evento;
import com.criscamzagra.Eventos.application.usuario.model.Usuario;
import com.criscamzagra.Eventos.application.usuario.ports.out.UsuarioRepository;

@DataJpaTest
class EventoRepositoryTest {

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	Evento evento;
	Usuario usuario;

	@BeforeEach
	public void setUp() {
		usuario = new Usuario();
		usuario.setUserId(1L);
		usuario.setName("Juan");

		usuarioRepository.save(usuario);

		evento = new Evento();
		evento.setId(1L);
		evento.setName("Reunion");
		evento.setDate("24-05-2024");
		evento.setLocation("Cali");
		evento.setUsaurio(usuario);

	}

	@Test
	public void testSaveEvento() {
		Evento eventoSave = eventoRepository.save(evento);
		assertNotNull(eventoSave);
		assertEquals(eventoSave.getDate(), evento.getDate());

	}

	@Test
	public void testFindEventById() {
		Optional<Evento> eventoOptional = eventoRepository.findById(1L);
		assertTrue(eventoOptional.isPresent());
		assertEquals(eventoOptional.get().getDate(), evento.getDate());
	}

}
