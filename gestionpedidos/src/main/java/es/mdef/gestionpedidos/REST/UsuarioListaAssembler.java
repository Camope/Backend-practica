package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Usuario;

@Component
public class UsuarioListaAssembler implements RepresentationModelAssembler<Usuario, UsuarioListaModel> {
	
	@Override
	public UsuarioListaModel toModel(Usuario entity) {
		UsuarioListaModel model = new UsuarioListaModel();
		model.setNombre(entity.getNombre());
		model.setRole(entity.getRole());
		model.add(linkTo(methodOn(UsuarioController.class).getOne(entity.getId())).withSelfRel());
		return model;
	}

	public CollectionModel<UsuarioListaModel> toCollection(List<Usuario> lista) {
		CollectionModel<UsuarioListaModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}
}

