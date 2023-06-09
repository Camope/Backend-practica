package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("NO_ADMINISTRADOR")
public class NoAdministrador extends Usuario {

	public static enum Departamento {
		EMIES, CCESP
	}

	public static enum Tipo {
		alumno, docente, administracion
	}

	@NotNull(message="departamento es un campo obligatorio para un usuario no administrador")
	private Departamento departamento;
	@NotNull(message="tipo es un campo obligatorio para un usuario no administrador")
	private Tipo tipo;

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

}
