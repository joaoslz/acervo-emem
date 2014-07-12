package ma.cultura.emem.acervo.modelo;

import java.io.Serializable;

/**
 * Classe criada para evitar código duplicado, como o método equals.
 * 
 * @author thiago
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -709021304453168564L;

	public abstract Object getId();

	@Override
	public boolean equals(Object obj) {
		if (!getClass().isInstance(obj))
			return false;
		final BaseEntity other = (BaseEntity) obj;
		if (this.getId() == null || other.getId() == null)
			return false;
		return this.getId().equals(other.getId());
	}

	@Override
	public int hashCode() {
		if (getId() != null)
			return getId().hashCode();
		else
			return super.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " #" + getId().toString();
	}
}
