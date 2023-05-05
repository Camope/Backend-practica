package es.mdef.gestionpedidos.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DiscriminatorFormula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "USUARIOS")
@DiscriminatorFormula("CASE WHEN role=0 THEN 'ADMINISTRADOR' WHEN role=1 THEN 'NO_ADMINISTRADOR' end")
public class Usuario implements UserDetails { // implementa UserDetails para la securización

	private static final long serialVersionUID = 1L;  // Necesario para la parte de seguridad

	public static enum Role {
		administrador, noAdministrador
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@NotBlank(message = "nombre es un campo obligatorio para un usuario")
	private String nombre;
	@NotBlank(message = "username es un campo obligatorio para un usuario")
	@Column(unique = true)
	private String username;
	@NotBlank(message = "contraseña es un campo obligatorio para un usuario")
	private String password;
//	@NotBlank(message="role es un campo obligatorio para un usuario")
	private Role role;

	// Campos obligatorios para que funcione springboot security
	@Column(name = "cuenta_activa")
	private boolean accountNonExpired = true;
	@Column(name = "cuenta_desbloqueada")
	private boolean accountNonLocked = true;
	@Column(name = "credenciales_activas")
	private boolean credentialsNonExpired = true;
	@Column(name = "habilitada")
	private boolean enabled = true;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String nombreUsuario) {
		this.username = nombreUsuario;
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

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Usuario(" + id + ") " + nombre + ", nombre de Usuario: " + username + ", Rol: " + role;
	}

	@Transient // Le dice a Springboot que no debe persistir
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<SimpleGrantedAuthority>(
				Arrays.asList(new SimpleGrantedAuthority(getRole().toString())));
	}

}
