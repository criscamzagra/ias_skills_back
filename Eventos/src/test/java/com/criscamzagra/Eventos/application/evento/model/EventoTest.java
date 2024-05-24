package com.criscamzagra.Eventos.application.evento.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventoTest {


	Evento evento;
	
	@BeforeEach
	public void setUo() {
		evento = new Evento();
	}
	
	
	@Test
	public void testGetAndSetLocation() {
		String location="cali";
		evento.setLocation(location);
		assertEquals(location, evento.getLocation());
		
	}
	
	@Test
	public void testGetAndSetName() {
		String name="Test";
		evento.setName(name);
		assertEquals(name, evento.getName());
	}
	
	//Asi con los demas
	
	
}
