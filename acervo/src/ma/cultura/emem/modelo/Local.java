package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Local implements java.io.Serializable {

	private static final long serialVersionUID = -2155196826342300173L;
	@Id
    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }
}
