package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Relation(itemRelation = "pregunta")
public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel> {

	private String enunciado;
	private Usuario usuario;
	private FamiliaImpl familia;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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

	@Override
	public String toString() {
		return "PreguntaPostModel [enunciado=" + enunciado + ", usuario=" + usuario + ", familia=" + familia + "]";
	}

}