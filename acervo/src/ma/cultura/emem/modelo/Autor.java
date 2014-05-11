package ma.cultura.emem.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Autor.listarTodos", query = "from Autor a order by a.id desc"),
	@NamedQuery(name = "Autor.pesquisarPorNome", query = "from Autor a where a.nome like :nome order by a.nome asc"),
	@NamedQuery(name = "Autor.pesquisarPorNomeAutorArtigo", query = "from Autor a where a.ehAutorArtigo = true and a.nome like :nome order by a.nome asc")
})
public class Autor implements Serializable {

	private static final long serialVersionUID = 8385561660567611471L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private boolean ehAutorArtigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (nome == null ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Autor))
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	/**
	 * @return the ehAutorArtigo
	 */
	public boolean isEhAutorArtigo() {
		return ehAutorArtigo;
	}

	/**
	 * @param ehAutorArtigo the ehAutorArtigo to set
	 */
	public void setEhAutorArtigo(boolean ehAutorArtigo) {
		this.ehAutorArtigo = ehAutorArtigo;
	}
}
