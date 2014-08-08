package ma.cultura.emem.acervo.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ma.cultura.emem.acervo.modelo.BaseEntity;

@Entity
public class Idioma extends BaseEntity {

	private static final long serialVersionUID = -8054954052714813400L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false, unique = true)
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