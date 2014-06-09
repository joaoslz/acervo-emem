package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Autor.findAll", query = "from Autor a order by a.id desc"),
	@NamedQuery(name = "Autor.findByNome", query = "from Autor a where a.nome like :nome order by a.nome asc")
})
public class Autor extends BaseEntity {

	private static final long serialVersionUID = 8385561660567611471L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100, nullable=false)
	private String nome;

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
	public String toString() {
		return this.nome;
	}
}
