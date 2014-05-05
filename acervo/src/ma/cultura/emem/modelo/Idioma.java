package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Idioma.NAMED_QUERY_LISTAR_TODOS, query = "from Idioma i order by i.nome asc") })
public class Idioma {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Idioma.listarTodos";

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;

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
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Idioma))
			return false;
		Idioma other = (Idioma) obj;
		return this.getId().equals(other.getId());
	}

	@Override
	public String toString() {
		return nome;
	}
}
