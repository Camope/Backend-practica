package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "familias")
public class FamiliaListaModel extends RepresentationModel<FamiliaListaModel> {

	private String enunciado;
	private Long tamano;
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public Long getTamano() {
		return tamano;
	}
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}
	
	@Override
	public String toString() {
		return "FamiliaModel [enunciado=" + enunciado + ", tamano=" + tamano + "]";
	}
}
