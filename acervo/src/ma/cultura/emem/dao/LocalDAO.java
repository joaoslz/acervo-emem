package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Local;

public class LocalDAO extends DAO<Local> {

	private static final long serialVersionUID = -2514667771922077887L;

	public LocalDAO() {
		super(Local.class);
	}

	public List<Local> pesquisarPorNome(String nome) {
		TypedQuery<Local> query = em.createNamedQuery(Local.NAMED_QUERY_PESQUISAR_POR_NOME, Local.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
