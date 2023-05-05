package es.mdef.gestionpedidos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PREGUNTAS")
public class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@NotNull(message="usuario es un campo obligatorio para una pregunta")
	private Usuario usuario;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@NotNull(message="familia es un campo obligatorio para una pregunta")
	private FamiliaImpl familia;
	@NotBlank(message="enunciado es un campo obligatorio para una pregunta")
	private String enunciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public FamiliaImpl getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaImpl familia) {
		this.familia = familia;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", usuario=" + usuario + ", enunciado=" + enunciado + "]";
	}

}
