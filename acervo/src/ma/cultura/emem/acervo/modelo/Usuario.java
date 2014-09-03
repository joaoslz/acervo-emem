package ma.cultura.emem.acervo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Usuario extends BaseEntity {

	private static final long serialVersionUID = -3566386447305683822L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
//	@Column(unique = true)
//	@CPF(message="CPF inválido!")
	private String cpf;
//	@Column(unique = true)
//	@Email(message="Email inválido!")
	private String email;
	private String telefone;

	@Override
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nome + " [" +email+ "]";
	}
	
}