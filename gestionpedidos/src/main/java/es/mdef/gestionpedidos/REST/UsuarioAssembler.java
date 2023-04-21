package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.entidades.Usuario.Role;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, UsuarioModel> {

	@Override
	public UsuarioModel toModel(Usuario entity) {
		UsuarioModel model = new UsuarioModel();

		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		model.setRole(entity.getRole());

		if (entity.getRole() == Role.administrador) {
			model.setTelefono(((Administrador)entity).getTelefono());
		} else if (entity.getRole() == Role.noAdministrador) {
			model.setDepartamento(((NoAdministrador)entity).getDepartamento());
			model.setTipo(((NoAdministrador)entity).getTipo());
		}
		model.add(linkTo(methodOn(UsuarioController.class).getOne(entity.getId())).withSelfRel());

		return model;
	}

	public Usuario toEntity(UsuarioPostModel model) {
		
		Usuario usuario = null;
		
		if (model.getRole() == Role.administrador) {
			Administrador usuarioAdministrador = new Administrador();
			usuarioAdministrador.setTelefono(model.getTelefono());
			usuario = usuarioAdministrador;
		} else if (model.getRole() == Role.noAdministrador) {
			NoAdministrador usuarioNoAdministrador = new NoAdministrador();
			usuarioNoAdministrador.setDepartamento(model.getDepartamento());
			usuarioNoAdministrador.setTipo(model.getTipo());
			usuario = usuarioNoAdministrador;
		}
		
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		usuario.setPassword(model.getPassword());
		usuario.setRole(model.getRole());

		return usuario;
	}

}
