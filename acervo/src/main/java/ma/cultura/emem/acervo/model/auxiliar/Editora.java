package ma.cultura.emem.acervo.model.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Editora extends AuxiliarEntity {
	
	private static final long serialVersionUID = 4825167929810486519L;
	
	@Column(length = 100)
	private String site;
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	@Override
	public void setNome(String nome) {
		if (nome != null)
			super.setNome(nome.toUpperCase());
	}
	
}
