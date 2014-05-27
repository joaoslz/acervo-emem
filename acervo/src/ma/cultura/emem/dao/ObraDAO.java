package ma.cultura.emem.dao;


import javax.inject.Inject;

import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;

public class ObraDAO extends DAO<Obra> {
	private static final long serialVersionUID = -2807083787187739746L;
	@Inject
	private Logger logger;
	
	public ObraDAO() {
		super(Obra.class);
	}
	
}