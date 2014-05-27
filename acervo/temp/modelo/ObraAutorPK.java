package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the obra_autor database table.
 * 
 */
@Embeddable
public class ObraAutorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="autores_id", insertable=false, updatable=false)
	private int autoresId;

	@Column(name="obra_item_acervo_id", insertable=false, updatable=false)
	private int obraItemAcervoId;

	public ObraAutorPK() {
	}
	public int getAutoresId() {
		return this.autoresId;
	}
	public void setAutoresId(int autoresId) {
		this.autoresId = autoresId;
	}
	public int getObraItemAcervoId() {
		return this.obraItemAcervoId;
	}
	public void setObraItemAcervoId(int obraItemAcervoId) {
		this.obraItemAcervoId = obraItemAcervoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ObraAutorPK)) {
			return false;
		}
		ObraAutorPK castOther = (ObraAutorPK)other;
		return 
			(this.autoresId == castOther.autoresId)
			&& (this.obraItemAcervoId == castOther.obraItemAcervoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.autoresId;
		hash = hash * prime + this.obraItemAcervoId;
		
		return hash;
	}
}