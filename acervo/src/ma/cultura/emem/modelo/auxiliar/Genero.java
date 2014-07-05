package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Genero extends BaseAuxiliarEntity {

	private static final long serialVersionUID = -6411960460196998100L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull(message="Nome é um campo obrigatório")
    @Size(min=2, max=100, message="O nome deve possuir no mínimo 2 e no máximo 100 caracteres")
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

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}