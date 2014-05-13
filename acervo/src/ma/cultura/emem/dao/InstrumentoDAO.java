package ma.cultura.emem.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Instrumento;

import org.apache.log4j.Logger;

public class InstrumentoDAO extends DAO<Instrumento> {

	private static final long serialVersionUID = 2478943600606404788L;
	private static final Logger LOGGER = Logger.getLogger(InstrumentoDAO.class);

	public InstrumentoDAO() {
		super(Instrumento.class);
	}

	public List<Instrumento> pesquisarPorNome(String nome) {
		LOGGER.debug("pesquisando instrumento por nome: " + nome);
		TypedQuery<Instrumento> query = em.createNamedQuery("Instrumento.pesquisarPorNome", Instrumento.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
