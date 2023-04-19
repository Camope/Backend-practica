package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.entidades.Usuario.Role;

@Relation(itemRelation = "usuario")  //define la etiqueta después de _embedded para el usuario retornado 
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

	private String nombre;
	private String nombreUsuario;
	private Departamento departamento;
	private Tipo tipo;
	private String telefono;
	private Role role;
	private Link preguntas;
	private Link familias;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Link getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Link preguntas) {
		this.preguntas = preguntas;
	}

	public Link getFamilias() {
		return familias;
	}

	public void setFamilias(Link familias) {
		this.familias = familias;
	}
	
	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", telefono=" + telefono
				+ ", Role=" + role + "]";
	}

}
