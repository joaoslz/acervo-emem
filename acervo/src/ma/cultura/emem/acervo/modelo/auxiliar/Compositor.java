package ma.cultura.emem.acervo.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ma.cultura.emem.acervo.modelo.BaseEntity;

@Entity
public class Compositor extends BaseEntity {

	private static final long serialVersionUID = -3977327337402745000L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false)
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
