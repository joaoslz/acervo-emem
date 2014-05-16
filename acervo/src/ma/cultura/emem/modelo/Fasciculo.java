package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fasciculo implements Serializable {

	private static final long serialVersionUID = -1467686486369558017L;

	@Id
    @GeneratedValue
    private Integer id;

    private Short volume;
    private Short numero;
	private Short ano;
    private String periodo;

    private short paginaInicial;
    private short paginaFinal;
    
    private String mesInicio;
    private String mesFim;
    
    @ManyToOne
    private Periodico periodico;
    
	@OneToMany(mappedBy = "fasciculo")
	private List<Exemplar> exemplares;
		
	
	@OneToMany(mappedBy = "fasciculo", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Artigo> artigos = new ArrayList<Artigo>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(short paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public short getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(short paginaFinal) {
        this.paginaFinal = paginaFinal;
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
    
	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public void adicionaArtigo(Artigo artigo) {
		artigos.add(artigo);
		artigo.setFasciculo(this);
	}

}
