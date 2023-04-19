package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NO_ADMINISTRADOR")
public class NoAdministrador extends Usuario {

	public static enum Departamento {
		EMIES, CCESP
	}

	public static enum Tipo {
		alumno, docente, administracion
	}

	private Departamento departamento;
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
