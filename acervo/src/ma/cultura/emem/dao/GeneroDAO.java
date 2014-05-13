package ma.cultura.emem.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Genero;

import org.apache.log4j.Logger;

public class GeneroDAO extends DAO<Genero> {

	private static final long serialVersionUID = -8906070558967086505L;
	@Inject
	private Logger logger;

	public GeneroDAO() {
		super(Genero.class);
	}

	public List<Genero> pesquisarPorNome(String nome) {
		logger.debug("pesquisando genero por nome: " + nome);
		TypedQuery<Genero> query = em.createNamedQuery("Genero.pesquisarPorNome", Genero.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
