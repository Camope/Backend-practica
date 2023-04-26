package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.FamiliaImpl;

@Component
public class FamiliaAssembler implements RepresentationModelAssembler<FamiliaImpl, FamiliaModel> {
	
	@Override
	public FamiliaModel toModel(FamiliaImpl entity) {
		FamiliaModel model = new FamiliaModel();
		model.setEnunciado(entity.getEnunciado());
		model.setTamano(entity.getTamano());
		model.add(linkTo(methodOn(FamiliaController.class).getOne(entity.getId())).withSelfRel());
		return model;
	}

}

