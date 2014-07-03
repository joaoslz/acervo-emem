package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.auxiliar.Periodico;

public class PeriodicoDAO extends DAO<Periodico> {

	private static final long serialVersionUID = -2071653371857004901L;

	public PeriodicoDAO() {
		super(Periodico.class);
	}

	public List<Periodico> findByNome(String nome) {
		TypedQuery<Periodico> query = em.createNamedQuery("Periodico.findByNome", Periodico.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}
}
