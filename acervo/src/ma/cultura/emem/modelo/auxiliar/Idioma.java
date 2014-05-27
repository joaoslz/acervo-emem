package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({ @NamedQuery(name = "Idioma.findAll", query = "from Idioma i order by i.nome asc") })
public class Idioma extends BaseEntity {

	private static final long serialVersionUID = -8054954052714813400L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;

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

	@Override
	public String toString() {
		return nome;
	}
}
