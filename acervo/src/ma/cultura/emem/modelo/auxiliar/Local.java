package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.Id;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
public class Local extends BaseEntity {

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
