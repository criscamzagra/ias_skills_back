package com.criscamzagra.Eventos.application.evento.model;

import com.criscamzagra.Eventos.application.usuario.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private String location;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "userId", nullable = false)
	private Usuario usaurio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Usuario getUsaurio() {
		return usaurio;
	}

	public void setUsaurio(Usuario usaurio) {
		this.usaurio = usaurio;
	}
	
	
	
	

}
