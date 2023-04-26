package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.entidades.Usuario.Role;

@Relation(itemRelation = "usuario")  //define la etiqueta después de _embedded para el usuario retornado 
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

	private String nombre;
	private String username;
	private Departamento departamento;
	private Tipo tipo;
	private String telefono;
	private Role role;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String nombreUsuario) {
		this.username = nombreUsuario;
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

	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + ", nombreUsuario=" + username + ", telefono=" + telefono
				+ ", Role=" + role + "]";
	}

}
