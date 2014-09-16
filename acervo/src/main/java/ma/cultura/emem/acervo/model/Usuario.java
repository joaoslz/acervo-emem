package ma.cultura.emem.acervo.model;

import javax.persistence.Entity;

import ma.cultura.emem.acervo.model.auxiliar.AuxiliarEntity;

import org.hibernate.validator.constraints.Email;

import br.com.caelum.stella.bean.validation.CPF;

@Entity
public class Usuario extends AuxiliarEntity {

	private static final long serialVersionUID = 879436850825266203L;

	// @Column(unique = true)
	@CPF(message = "Inválido!", formatted=true)
	private String cpf;
	// @Column(unique = true)
	@Email(message = "Inválido!")
	private String email;
	private String telefone;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return getNome() + " [" + email + "]";
	}
}