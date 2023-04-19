package es.mdef.gestionpedidos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.Pregunta;

public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {

	List<Pregunta> findPreguntaByUsuarioId(Long id);
}
