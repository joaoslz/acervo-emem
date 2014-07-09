package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Assunto extends BaseAuxiliarEntity {

	private static final long serialVersionUID = 819805863730128933L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false, unique = true)
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
