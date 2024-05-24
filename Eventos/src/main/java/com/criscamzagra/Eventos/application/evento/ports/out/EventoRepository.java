package com.criscamzagra.Eventos.application.evento.ports.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.criscamzagra.Eventos.application.evento.model.Evento;

@Repository
public interface EventoRepository  extends JpaRepository<Evento, Long>{
/*	@Query("SELECT e FROM evento e WHERE e.user_id = :userId")
	 List<Evento> findEventsByIdUsers(@Param("userId") Long userId ); */

}
