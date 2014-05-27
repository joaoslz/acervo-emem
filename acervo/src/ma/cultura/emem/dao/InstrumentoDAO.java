package ma.cultura.emem.dao;


import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.auxiliar.Instrumento;

import org.apache.log4j.Logger;

public class InstrumentoDAO extends DAO<Instrumento> {

	private static final long serialVersionUID = 2478943600606404788L;
	@Inject
	private Logger logger;

	public InstrumentoDAO() {
		super(Instrumento.class);
	}

	public List<Instrumento> findByNome(String nome) {
		logger.debug("pesquisando instrumento por nome: " + nome);
		TypedQuery<Instrumento> query = em.createNamedQuery("Instrumento.findByNome", Instrumento.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
