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
	@NamedQuery(name = Editora.NAMED_QUERY_LISTAR_TODOS, query = "select e from Editora e order by e.id desc"),
	@NamedQuery(name = Editora.NAMED_QUERY_PESQUISAR_POR_NOME, query = "select e from Editora e where e.nome like :nome order by e.nome asc") })
public class Editora implements Serializable{

	public static final String NAMED_QUERY_LISTAR_TODOS = "Editora.listarTodos";
	public static final String NAMED_QUERY_PESQUISAR_POR_NOME = "Editora.pesquisarPorNome";

	private static final long serialVersionUID = -7971911155752471160L;
	
    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private String site;

    @OneToMany(mappedBy = "editora")
    private List<Obra> obras = new ArrayList<Obra>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
	if(!(obj instanceof Editora))
	    return false;
	Editora ed = (Editora) obj;
	return this.id == ed.id;
    }
}
