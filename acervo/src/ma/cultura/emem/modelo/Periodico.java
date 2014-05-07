package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "periodico")
@NamedQueries({ @NamedQuery(name = Periodico.NAMED_QUERY_LISTAR_TODOS, query = "from Periodico p order by p.id desc") })
public class Periodico extends Obra {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Periodico.listarTodos";

	private static final long serialVersionUID = -112232541131788319L;

	private String issn;

	private Boolean ehAssinado;

	@OneToMany(mappedBy = "periodico", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Artigo> artigos = new ArrayList<Artigo>();

	public Periodico() {
		// TODO Auto-generated constructor stub
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public void adicionaArtigo(Artigo artigo) {
		artigos.add(artigo);
		artigo.setPeriodico(this);
	}

	public Boolean getEhAssinado() {
		return ehAssinado;
	}

	public void setEhAssinado(Boolean ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

	public String getArtigosToString() {
		StringBuilder builder = new StringBuilder();
		if (getArtigos() != null && !getArtigos().isEmpty()) {
			Iterator<Artigo> it = getArtigos().iterator();
			while (it.hasNext()) {
				Artigo a = it.next();
				builder.append(a.getTitulo());
				if (it.hasNext()) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
}