package es.mdef.gestionpedidos.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
//	private final UsuarioListaAssembler listaAssembler;
	private final Logger log;
	
	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		//this.listaAssembler = listaAssembler;
		//log = GestionPedidosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Usuario> one(@PathVariable String nombre) {
		Usuario usuario = repositorio.findUsuarioByNombre(nombre)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
	//	log.info("Recuperado " + pedido);
		return assembler.toModel(usuario);
	}
	
	
}

/*
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	private final PedidoRepositorio repositorio;
	private final PedidoAssembler assembler;
	private final PedidoListaAssembler listaAssembler;
	private final Logger log;
	
	PedidoController(PedidoRepositorio repositorio, PedidoAssembler assembler, 
			PedidoListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		log = GestionPedidosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Pedido> one(@PathVariable Long id) {
		Pedido pedido = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
		log.info("Recuperado " + pedido);
		return assembler.toModel(pedido);
	}
	
	@GetMapping
	public CollectionModel<PedidoListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@GetMapping("porEstado")
	public CollectionModel<PedidoListaModel> pedidosPorEstado(@RequestParam PedidoEstado estado) {
		return listaAssembler.toCollection(
				repositorio.findPedidoByEstado(estado)
				);
	}
	
	@PostMapping
	public EntityModel<Pedido> add(@RequestBody PedidoModel model) {
		Pedido pedido = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + pedido);
		return assembler.toModel(pedido);
	}
	
	@PutMapping("{id}")
	public EntityModel<Pedido> edit(@PathVariable Long id, @RequestBody PedidoModel model) {
		Pedido pedido = repositorio.findById(id).map(ped -> {
			ped.setDescripcion(model.getDescripcion());
			ped.setEstado(model.getEstado());
			return repositorio.save(ped);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
		log.info("Actualizado " + pedido);
		return assembler.toModel(pedido);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado pedido " + id);
		repositorio.deleteById(id);
	}
}*/