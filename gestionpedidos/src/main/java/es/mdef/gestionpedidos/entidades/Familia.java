package es.mdef.gestionpedidos.entidades;

public class Familia {
	
	private String enunciado;

	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@Override
	public String toString() {
		return "Familia [enunciado=" + enunciado + "]";
	}
	
}
