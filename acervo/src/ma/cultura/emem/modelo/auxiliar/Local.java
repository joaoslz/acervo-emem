package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Local extends BaseAuxiliarEntity {

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
