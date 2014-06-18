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
	@NamedQuery(name = "Cantor.findAll", query = "from Cantor c order by c.id desc"),
	@NamedQuery(name = "Cantor.findByNome", query = "from Cantor c where c.nome like :nome order by c.nome asc")
})
public class Cantor extends BaseEntity {

	private static final long serialVersionUID = 4524222302654053946L;

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
