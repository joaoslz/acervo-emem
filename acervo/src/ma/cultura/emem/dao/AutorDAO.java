package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Autor;

public class AutorDAO extends DAO<Autor> {

	private static final long serialVersionUID = 8780779436249062614L;

	public AutorDAO() {
		super(Autor.class);
	}

	public List<Autor> pesquisarPorNome(String nome) {
		TypedQuery<Autor> query = em.createNamedQuery(Autor.NAMED_QUERY_PESQUISAR_POR_NOME, Autor.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
