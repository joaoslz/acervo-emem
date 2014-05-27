package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Instrumento.findAll", query = "from Instrumento i order by i.id desc"),
	@NamedQuery(name = "Instrumento.findByNome", query = "from Instrumento i where i.nome like :nome order by i.nome asc")
})
public class Instrumento extends BaseEntity {

	private static final long serialVersionUID = 5692334415877275946L;
	
	@Id
	@GeneratedValue
	private Integer id;
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