package ma.cultura.emem.acervo.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe criada para evitar código duplicado, como o método equals.
 *
 * @author thiago
 */
@MappedSuperclass
public abstract class RootEntity implements Serializable {
	
	private static final long serialVersionUID = 1197324139535864009L;
	@Id
	@GeneratedValue
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof RootEntity)) {
			return false;
		}
		final RootEntity other = (RootEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return getClass() + " # " + getId();
	}
}
