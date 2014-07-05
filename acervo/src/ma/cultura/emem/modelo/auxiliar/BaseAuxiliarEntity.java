package ma.cultura.emem.modelo.auxiliar;

import ma.cultura.emem.modelo.BaseEntity;

/**
 * Classe criada para evitar código duplicado, como o método equals.
 * 
 * @author thiago
 */
public abstract class BaseAuxiliarEntity extends BaseEntity {

	private static final long serialVersionUID = -4220713091057445143L;

	public abstract String getNome();
}
