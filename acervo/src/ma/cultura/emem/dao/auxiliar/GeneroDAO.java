package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Genero;

public class GeneroDAO extends DAO<Genero> {

	private static final long serialVersionUID = -8906070558967086505L;

	public GeneroDAO() {
		super(Genero.class);
	}

	public List<Genero> findByNome(String nome) {
		logger.debug("pesquisando genero por nome: " + nome);
		TypedQuery<Genero> query = em.createNamedQuery("Genero.findByNome", Genero.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
