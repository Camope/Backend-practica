package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pregunta;

@Component
public class PreguntaListaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaModel> {
	
	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(linkTo(methodOn(PreguntaController.class).getOne(entity.getId())).withSelfRel());
		return model;
	}

	public CollectionModel<PreguntaModel> toCollection(List<Pregunta> lista) {
		CollectionModel<PreguntaModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		collection.add(linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas"));
		return collection;
	}
}

