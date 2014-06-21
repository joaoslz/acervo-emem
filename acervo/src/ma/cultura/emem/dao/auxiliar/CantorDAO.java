package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Cantor;

public class CantorDAO extends DAO<Cantor> {

	private static final long serialVersionUID = 8780779436249062614L;

	public CantorDAO() {
		super(Cantor.class);
	}

	public List<Cantor> findByNome(String nome) {
		TypedQuery<Cantor> query = em.createNamedQuery("Cantor.findByNome", Cantor.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
