package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "preguntas")
public class PreguntaListaModel extends RepresentationModel<PreguntaListaModel> {

	private String enunciado;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@Override
	public String toString() {
		return "PreguntaListaModel [enunciado=" + enunciado + "]";
	}

}
