package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.GestionpedidosApplication;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.repositorios.PreguntaRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	private final PreguntaAssembler assembler;
	private final PreguntaListaAssembler listaAssembler;
	private final Logger log;
	
	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler assembler, PreguntaListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		log = GestionpedidosApplication.log;
	}

	@GetMapping("{id}")
	public PreguntaModel getOne(@PathVariable Long id) {
		Pregunta pregunta = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
		log.info("Recuperada " + pregunta);
		return assembler.toModel(pregunta);
	}
	
	@PutMapping("{id}")
	public PreguntaModel edit(@PathVariable Long id, @Valid @RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.findById(id).map(p -> {
		
			p.setEnunciado(model.getEnunciado());
			p.setUsuario(model.getUsuario());
			p.setFamilia(model.getFamilia());
			
			return repositorio.save(p);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "pretunta"));
		log.info("Actualizada " + pregunta);
		return assembler.toModel(pregunta);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrada pregunta " + id);
		repositorio.deleteById(id);
	}
	
	@GetMapping
	public CollectionModel<PreguntaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll())
				.add(linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas"));
	}
	
	@PostMapping
	public PreguntaModel add(@Valid @RequestBody PreguntaPostModel model) {
		Pregunta pregunta = new Pregunta();
		
		pregunta.setEnunciado(model.getEnunciado());
		pregunta.setUsuario(model.getUsuario());
		pregunta.setFamilia(model.getFamilia());
		
		repositorio.save(pregunta);
		log.info("Añadida " + pregunta);
		return assembler.toModel(pregunta);
	}
	
}