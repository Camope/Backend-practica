package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

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
import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.repositorios.FamiliaRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/familias")
public class FamiliaController {
	private final FamiliaRepositorio repositorio;
	private final FamiliaAssembler assembler;
	private final FamiliaListaAssembler listaAssembler;
	private final PreguntaListaAssembler preguntaListaAssembler;
	private final UsuarioListaAssembler usuarioListaAssembler;
	private final Logger log;
	
	FamiliaController(FamiliaRepositorio repositorio, FamiliaAssembler assembler, FamiliaListaAssembler listaAssembler,
			PreguntaListaAssembler preguntaListaAssembler, UsuarioListaAssembler usuarioListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.preguntaListaAssembler = preguntaListaAssembler;
		this.usuarioListaAssembler = usuarioListaAssembler;
		log = GestionpedidosApplication.log;
	}

	@GetMapping("{id}")
	public FamiliaModel getOne(@PathVariable Long id) {
		FamiliaImpl familia = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
		log.info("Recuperada " + familia);
		return assembler.toModel(familia);

	}

	@PutMapping("{id}")
	public FamiliaModel edit(@PathVariable Long id, @Valid @RequestBody FamiliaPostModel model) {
		FamiliaImpl familia = repositorio.findById(id).map(f -> {
		
			f.setEnunciado(model.getEnunciado());
			
			return repositorio.save(f);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
		log.info("Actualizada " + familia);
		return assembler.toModel(familia);
	}
	
	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> getPreguntas(@PathVariable Long id) {
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario")).getPreguntas();
		
		return preguntaListaAssembler.toCollection(preguntas)
				.add(linkTo(methodOn(PreguntaController.class).getOne(id)).slash("preguntas").withRel("preguntasPorFamilia"));

	}
	
	@GetMapping("{id}/usuarios")
	public CollectionModel<UsuarioListaModel> getUsers(@PathVariable Long id) {
		
		List<Usuario> usuarios = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "familia"))
				.getPreguntas()
				.stream()
				.map(Pregunta::getUsuario)
				.distinct()
				.collect(Collectors.toList());

		return usuarioListaAssembler.toCollection(usuarios)
				.add(linkTo(methodOn(FamiliaController.class).getOne(id)).slash("usuarios").withRel("UsuariosPorFamilia"));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrada familia " + id);
		repositorio.deleteById(id);
	}
	
	@GetMapping
	public CollectionModel<FamiliaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping
	public FamiliaModel add(@Valid @RequestBody FamiliaPostModel model) {
		FamiliaImpl familia = new FamiliaImpl();
		
		familia.setEnunciado(model.getEnunciado());
		System.out.println("model: " + model.getEnunciado() + "; familia: " + familia.getEnunciado());
		
		repositorio.save(familia);
		log.info("Añadida " + familia);
		return assembler.toModel(familia);
	}
	
	
	

}