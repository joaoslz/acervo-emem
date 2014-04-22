package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Assunto;

public class AssuntoDAO extends DAO<Assunto> {

	private static final long serialVersionUID = 649481248354589573L;

	public AssuntoDAO() {
		super(Assunto.class);
	}

	public List<Assunto> pesquisarPorAssunto(String assunto) {
		TypedQuery<Assunto> query = em.createNamedQuery(Assunto.NAMED_QUERY_PESQUISAR_POR_ASSUNTO, Assunto.class);
		query.setParameter("assunto", "%" + assunto + "%");
		return query.getResultList();
	}

}
