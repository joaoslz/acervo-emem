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
	@NamedQuery(name = "Genero.findAll", query = "from Genero g order by g.id desc"),
	@NamedQuery(name = "Genero.findByNome", query = "from Genero g where g.nome like :nome order by g.nome asc")
})
public class Genero extends BaseEntity{

	private static final long serialVersionUID = -6411960460196998100L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100, nullable=false, unique=true )
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
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