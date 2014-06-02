package ma.cultura.emem.modelo.auxiliar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ma.cultura.emem.modelo.BaseEntity;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "TipoObra.findAll", query = "from TipoObra t order by t.nome asc")
})
public class TipoObra extends BaseEntity {

	private static final long serialVersionUID = 8385561660567611471L;
	
	@Id
	private Integer id;
	
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

	public void setId(Integer id) {
		this.id = id;
	}
}
