package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Local;

public class LocalDAO extends DAO<Local> {

	private static final long serialVersionUID = -2514667771922077887L;

	public LocalDAO() {
		super(Local.class);
	}

	public List<Local> findByNome(String nome) {
		TypedQuery<Local> query = em.createNamedQuery("Local.findByNome", Local.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
