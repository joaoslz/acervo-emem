package ma.cultura.emem.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MusicaId implements Serializable {

	private static final long serialVersionUID = -8669532704023815911L;
	private int cd;
	private int faixa;

	public int getFaixa() {
		return faixa;
	}

	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cd;
		result = prime * result + faixa;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MusicaId)) {
			return false;
		}
		MusicaId other = (MusicaId) obj;
		if (cd != other.cd) {
			return false;
		}
		if (faixa != other.faixa) {
			return false;
		}
		return true;
	}
}