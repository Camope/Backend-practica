package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.GestionpedidosApplication;
import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario.Role;
import es.mdef.gestionpedidos.repositorios.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@PersistenceContext
	EntityManager em;
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
	private final UsuarioListaAssembler listaAssembler;
	private final PreguntaListaAssembler preguntaListaAssembler;
	private final FamiliaListaAssembler familiaListaAssembler;
	private final Logger log;

	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, UsuarioListaAssembler listaAssembler,
			PreguntaListaAssembler preguntaListaAssembler, FamiliaListaAssembler familiaListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.preguntaListaAssembler = preguntaListaAssembler;
		this.familiaListaAssembler = familiaListaAssembler;

		log = GestionpedidosApplication.log;
	}

	@GetMapping("{id}")
	public UsuarioModel getOne(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id).orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		return assembler.toModel(usuario);
	}

	@PutMapping("{id}")
	public UsuarioModel edit(@PathVariable Long id, @Valid @RequestBody UsuarioPutModel model) {

		int n_regs = 0;

		if (model.getRole() == Role.administrador) {
			n_regs = repositorio.update(model.getNombre(), model.getUsername(), model.getRole().ordinal(),
					model.getTelefono(), null, null, id);
		} else if (model.getRole() == Role.noAdministrador) {
			n_regs = repositorio.update(model.getNombre(), model.getUsername(), model.getRole().ordinal(), null,
					model.getDepartamento().ordinal(), model.getTipo().ordinal(), id);
		}
		
		if (n_regs == 0) {
			throw new RegisterNotFoundException(id, "No se han podido modificar los datos");
		}

		Usuario usuario = repositorio.findById(id).orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		
		log.info("Actualizado " + usuario);

		return assembler.toModel(usuario);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado usuario " + id);
		repositorio.deleteById(id);
	}

	@GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll())
				.add(linkTo(methodOn(UsuarioController.class).all()).withRel("usuarios"));
	}

	@PostMapping
	public UsuarioModel add(@Valid @RequestBody UsuarioPostModel model) {
		model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
		Usuario usuario = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + usuario);
		return assembler.toModel(usuario);
	}

	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> getPreguntas(@PathVariable Long id) {
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario")).getPreguntas();

		return preguntaListaAssembler.toCollection(preguntas).add(
				linkTo(methodOn(UsuarioController.class).getOne(id)).slash("preguntas").withRel("preguntasPorUsuario"));
	}

	@GetMapping("{id}/familias")
	public CollectionModel<FamiliaListaModel> getFamilies(@PathVariable Long id) {

		List<FamiliaImpl> familias = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario")).getPreguntas().stream()
				.map(Pregunta::getFamilia).distinct().collect(Collectors.toList());

		return familiaListaAssembler.toCollection(familias).add(
				linkTo(methodOn(UsuarioController.class).getOne(id)).slash("familias").withRel("familiasPorUsuario"));
	}

	@PatchMapping("{id}/password")
	public void passChange(@PathVariable Long id, @Valid @RequestBody String password) {

		Usuario usuarioActualizado = repositorio.findById(id).map(u -> {
			u.setPassword(new BCryptPasswordEncoder().encode(password));
			return repositorio.save(u);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		
		log.info("Cambiada la contraseña del usuario " + usuarioActualizado);
	}

}
