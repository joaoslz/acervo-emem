package ma.cultura.emem.acervo.modelo.auxiliar;

import javax.faces.view.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ma.cultura.emem.acervo.modelo.BaseEntity;

@Entity
@ViewScoped
public class Cantor extends BaseEntity {

	private static final long serialVersionUID = 4524222302654053946L;

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
