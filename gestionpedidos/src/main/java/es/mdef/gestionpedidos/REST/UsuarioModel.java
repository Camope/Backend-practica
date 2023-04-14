package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "usuario")
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

	private String nombre;
	private String nombreUsuario;
//	private Departamento departamento;
//	private Tipo tipo;
	private String telefono;
	private String role;

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

//	public Departamento getDepartamento() {
//		return departamento;
//	}
//
//	public void setDepartamento(Departamento departamento) {
//		this.departamento = departamento;
//	}
//
//	public Tipo getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(Tipo tipo) {
//		this.tipo = tipo;
//	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", telefono=" + telefono
				+ ", Role=" + role + "]";
	}

}
