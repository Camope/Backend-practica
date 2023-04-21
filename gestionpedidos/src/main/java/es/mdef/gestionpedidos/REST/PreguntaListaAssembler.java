package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pregunta;

@Component
public class PreguntaListaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaModel> {
	
	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		model.setEnunciado(entity.getEnunciado());
		//model.add(linkTo(methodOn(UsuarioController.class).getOne(entity.getId())).withSelfRel());
		return model;
	}

}

