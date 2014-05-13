package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Editora.listarTodos", query = "select e from Editora e order by e.id desc"),
	@NamedQuery(name = "Editora.pesquisarPorNome", query = "select e from Editora e where e.nome like :nome order by e.nome asc") })
public class Editora implements Serializable{

	private static final long serialVersionUID = -7971911155752471160L;
	
    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
    private String site;

    @OneToMany(mappedBy = "editora")
    private List<Obra> obras = new ArrayList<Obra>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
	if(nome != null) {
	    this.nome = nome.toUpperCase();
	}else{
	    this.nome = null;
	}
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

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Editora))
			return false;
		Editora other = (Editora) obj;
		if(this.getId() == null || other.getId() == null)
			return false;
		return this.getId().equals(other.getId());
	}
}
