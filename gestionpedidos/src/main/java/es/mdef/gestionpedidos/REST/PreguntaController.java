package es.mdef.gestionpedidos.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.GestionpedidosApplication;
import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.entidades.Usuario.Role;
import es.mdef.gestionpedidos.repositorios.PreguntaRepositorio;
import es.mdef.gestionpedidos.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	//private final PreguntaAssembler assembler;
	//private final UsuarioListaAssembler listaAssembler;
	private final Logger log;
	
	PreguntaController(PreguntaRepositorio repositorio){//, PreguntaAssembler assembler) {
		this.repositorio = repositorio;
		//this.assembler = assembler;
		//this.listaAssembler = listaAssembler;
		log = GestionpedidosApplication.log;
	}

//	public CollectionModel<PreguntaListaModel> questionById(Long id){
//		return repositorio.findPreguntaByUsuarioId(id);
//		EntityModel<Pedido> model = EntityModel.of(entity);
//		model.add(
//				linkTo(methodOn(PedidoController.class).one(entity.getId())).withSelfRel()
//				);
//		return model;
//	}
	
//	@GetMapping("{id}")
//	public UsuarioModel getOne(@PathVariable Long id) {
//		Usuario usuario = repositorio.findById(id)
//				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
//		log.info("Recuperado " + usuario);
//		return assembler.toModel(usuario);
//	}
//	
//	@PutMapping("{id}")
//	public UsuarioModel edit(@PathVariable Long id, @RequestBody UsuarioPutModel model) {
//		Usuario usuario = repositorio.findById(id).map(u -> {
//			
//			u.setNombre(model.getNombre());
//			u.setNombreUsuario(model.getNombreUsuario());
//			u.setRole(model.getRole());
//			
//			if(u.getRole() == Role.administrador) {
//				((Administrador)u).setTelefono(model.getTelefono());
//			} else if(u.getRole() == Role.noAdministrador) {
//				((NoAdministrador)u).setDepartamento(model.getDepartamento());
//				((NoAdministrador)u).setTipo(model.getTipo());
//			}
//			
//			return repositorio.save(u);
//		})
//		.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
//		log.info("Actualizado " + usuario);
//		return assembler.toModel(usuario);
//	}
//	
//	@DeleteMapping("{id}")
//	public void delete(@PathVariable Long id) {
//		log.info("Borrado usuario " + id);
//		repositorio.deleteById(id);
//	}
//	
//	@GetMapping
//	public CollectionModel<UsuarioListaModel> all() {
//		return listaAssembler.toCollection(repositorio.findAll());
//	}
//	
//	@PostMapping
//	public UsuarioModel add(@RequestBody UsuarioPostModel model) {
//		Usuario usuario = repositorio.save(assembler.toEntity(model));
//		log.info("Añadido " + usuario);
//		return assembler.toModel(usuario);
//	}
//	
//	@GetMapping("{id}/preguntas")
//	public CollectionModel<PreguntaListaModel> questions() {
//		return listaAssembler.toCollection(repositorio.findAll());
//	}
}