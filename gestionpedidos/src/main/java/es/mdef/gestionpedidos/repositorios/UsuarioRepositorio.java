package es.mdef.gestionpedidos.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.mdef.gestionpedidos.entidades.Usuario;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query(
			value = "UPDATE USUARIOS SET NOMBRE=?1, USERNAME=?2, ROLE=?3, TELEFONO=?4, DEPARTAMENTO=?5, TIPO=?6 WHERE ID=?7,",
					//+ "CUENTA_ACTIVA=?8, CUENTA_DESBLOQUEADA=?9, CREDENCIALES_ACTIVAS=?10, HABILITADA=?11",
			nativeQuery = true
	)
	int update(String nombre, String username, Integer role, String telefono, Integer departamento, Integer tipo, Long id,
				boolean cuenta_activa, boolean cuenta_desbloqueada, boolean credenciales_activas, boolean habilitada);

}