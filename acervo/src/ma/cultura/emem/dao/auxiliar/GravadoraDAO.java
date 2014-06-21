package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Gravadora;

public class GravadoraDAO extends DAO<Gravadora> {

	private static final long serialVersionUID = 5329836075957207881L;

	public GravadoraDAO() {
		super(Gravadora.class);
	}

	public List<Gravadora> findByNome(String nome) {
		logger.debug("pesquisando gravadora por nome: " + nome);
		TypedQuery<Gravadora> query = em.createNamedQuery("Gravadora.findByNome", Gravadora.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}