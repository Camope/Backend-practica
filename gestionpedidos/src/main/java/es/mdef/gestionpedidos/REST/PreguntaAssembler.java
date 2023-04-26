package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pregunta;

@Component
public class PreguntaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaModel> {
	
	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(linkTo(methodOn(PreguntaController.class).getOne(entity.getId())).withSelfRel())
			.add(linkTo(methodOn(UsuarioController.class).getOne(entity.getUsuario().getId())).withSelfRel())
			.add(linkTo(methodOn(FamiliaController.class).getOne(entity.getFamilia().getId())).withSelfRel());
		
		return model;
	}

}

