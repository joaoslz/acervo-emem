package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editora {

    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private String site;

    @OneToMany(mappedBy = "editora")
    private final List<Obra> obras = new ArrayList<Obra>();

    public int getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSite() {
	return site;
    }

    public void setSite(String site) {
	this.site = site;
    }

    public List<Obra> getObras() {
	return obras;
    }

}
