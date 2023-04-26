package es.mdef.gestionpedidos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.entidades.Usuario.Role;
import jakarta.transaction.Transactional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	@Transactional
	@Modifying
	@Query(
			value = "UPDATE USUARIOS SET NOMBRE=?1, USERNAME=?2, ROLE=?3, TELEFONO=?4, DEPARTAMENTO=?5, TIPO=?6 WHERE ID=?7", 
			nativeQuery = true
	)
	int update(String nombre, String username, Integer role, String telefono, Integer departamento, Integer tipo, Long id);

}