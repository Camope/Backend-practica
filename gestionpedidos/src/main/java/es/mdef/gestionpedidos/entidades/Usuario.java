package es.mdef.gestionpedidos.entidades;

import java.util.List;

import org.hibernate.annotations.DiscriminatorFormula;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
@DiscriminatorFormula("CASE WHEN role=0 THEN 'ADMINISTRADOR' WHEN role=1 THEN 'NO_ADMINISTRADOR' end")
public class Usuario {
	
	public static enum Role {
		administrador,
		noAdministrador
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String nombre;
	private String nombreUsuario;
	private String password;
	private Role role;
	@OneToMany(mappedBy = "usuario")
	List<Pregunta> preguntas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> pedidos) {
		this.preguntas = pedidos;
	}

	@Override
	public String toString() {
		return "Usuario(" + id + ") " + nombre + ", nombre de Usuario: " + nombreUsuario + ", Rol: " + role;
	}

}
