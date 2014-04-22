package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "periodico")
@NamedQueries({ 
	@NamedQuery(name = "Periodico.listarTodos", query = "from Periodico p order by p.id desc")
})
public class Periodico extends Obra {

   
	private static final long serialVersionUID = -112232541131788319L;
	
	private String issn;
	
	private Boolean ehAssinado;
	
    @OneToMany(mappedBy = "periodico")
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
    }

	public Boolean getEhAssinado() {
		return ehAssinado;
	}

	public void setEhAssinado(Boolean ehAssinado) {
		this.ehAssinado = ehAssinado;
	}

}
