package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({
	@NamedQuery(name = "Assunto.findAll", query = "from Assunto a order by a.id desc"),
	@NamedQuery(name = "Assunto.findByNome", query = "from Assunto a where a.nome like :nome order by a.nome asc") })

public class Assunto extends BaseEntity{

	private static final long serialVersionUID = -884776973799815651L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;

	@Override
	public String toString() {
		return nome;
	}

	@Override
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
}
