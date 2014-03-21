package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "periodico")
public class Periodico extends Obra {

    private static final long serialVersionUID = -1L;

    private String issn;
    private short volume;

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

    public short getVolume() {
	return volume;
    }

    public void setVolume(short volume) {
	this.volume = volume;
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

}
