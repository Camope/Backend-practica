package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;

@Component
public class PreguntaListaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaListaModel> {
	
	@Override
	public PreguntaListaModel toModel(Pregunta entity) {
		PreguntaListaModel model = new PreguntaListaModel();
		model.setEnunciado(entity.getEnunciado());
		//model.add(linkTo(methodOn(UsuarioController.class).getOne(entity.getId())).withSelfRel());
		return model;
	}

}

