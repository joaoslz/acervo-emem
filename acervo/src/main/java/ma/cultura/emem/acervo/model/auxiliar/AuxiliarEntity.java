package ma.cultura.emem.acervo.model.auxiliar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ma.cultura.emem.acervo.model.RootEntity;

/**
 * Entity implementation class for Entity: AuxiliarEntity
 *
 */
@MappedSuperclass
public class AuxiliarEntity extends RootEntity {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 430843736746085940L;
	// , unique = true ???
	@Column(length = 100, nullable = false)
	private String nome;
	
	public AuxiliarEntity() {
		super();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
