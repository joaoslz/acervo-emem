package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = Local.NAMED_QUERY_LISTAR_TODOS, query = "from Local l order by l.nome desc"),
	@NamedQuery(name = Local.NAMED_QUERY_PESQUISAR_POR_NOME, query = "from Local l where l.nome like :nome order by l.nome asc") })
public class Local implements java.io.Serializable {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Local.listarTodos";
	public static final String NAMED_QUERY_PESQUISAR_POR_NOME = "Local.pesquisarPorNome";
	
	private static final long serialVersionUID = -2155196826342300173L;
	@Id
    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Local))
			return false;
		Local other = (Local) obj;
		if(this.getNome() == null || other.getNome() == null)
			return false;
		return this.getNome().equals(other.getNome());
	}
}
