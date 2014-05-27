package ma.cultura.emem.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the musica database table.
 * 
 */
@Embeddable
public class MusicaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int CD_id;

	private int faixa;

	public MusicaPK() {
	}
	public int getCD_id() {
		return this.CD_id;
	}
	public void setCD_id(int CD_id) {
		this.CD_id = CD_id;
	}
	public int getFaixa() {
		return this.faixa;
	}
	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MusicaPK)) {
			return false;
		}
		MusicaPK castOther = (MusicaPK)other;
		return 
			(this.CD_id == castOther.CD_id)
			&& (this.faixa == castOther.faixa);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.CD_id;
		hash = hash * prime + this.faixa;
		
		return hash;
	}
}