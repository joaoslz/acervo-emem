package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Assunto;

public class AssuntoDAO extends DAO<Assunto> {

	private static final long serialVersionUID = 649481248354589573L;

	public AssuntoDAO() {
		super(Assunto.class);
	}

	public List<Assunto> findByNome(String nome) {
		TypedQuery<Assunto> query = em.createNamedQuery("Assunto.findByNome", Assunto.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

}
