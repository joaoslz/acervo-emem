package ma.cultura.emem.acervo.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ma.cultura.emem.acervo.modelo.BaseEntity;

@Entity
public class TipoObra extends BaseEntity {

	private static final long serialVersionUID = 8385561660567611471L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false, unique = true)
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

	@Override
	public String toString() {
		return this.nome;
	}
}
