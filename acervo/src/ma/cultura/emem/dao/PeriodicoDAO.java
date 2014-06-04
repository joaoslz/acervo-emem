package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Periodico;

public class PeriodicoDAO extends DAO<Periodico> {

	private static final long serialVersionUID = -2071653371857004901L;

	public PeriodicoDAO() {
		super(Periodico.class);
	}
	
	public List<Periodico> findByTitulo(String titulo){
		TypedQuery<Periodico> query = em.createNamedQuery("Periodico.findByTitulo", Periodico.class);
		query.setParameter("titulo", "%"+titulo+"%");
		return query.getResultList();
	}

}
