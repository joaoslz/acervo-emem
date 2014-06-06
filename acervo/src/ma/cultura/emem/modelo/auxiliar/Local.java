package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({ @NamedQuery(name = "Local.findAll", query = "from Local l order by l.nome desc"),
		@NamedQuery(name = "Local.findByNome", query = "from Local l where l.nome like :nome order by l.nome asc") })
public class Local extends BaseEntity{

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
	public Object getId() {
		return nome;
	}
}
