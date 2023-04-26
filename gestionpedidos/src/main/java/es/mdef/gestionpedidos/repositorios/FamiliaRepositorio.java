package es.mdef.gestionpedidos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.FamiliaImpl;

public interface FamiliaRepositorio extends JpaRepository<FamiliaImpl, Long> {

}
