package ma.cultura.emem.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Livro;

import org.apache.log4j.Logger;

public class LivroDAO extends DAO<Livro> {
	
	private static final long serialVersionUID = -3418373953576656728L;
	@Inject
	private Logger logger;
	
	public LivroDAO() {
		super(Livro.class);
	}

	public List<Livro> pesquisarPorISBN(String isbn) {
		TypedQuery<Livro> query = em.createNamedQuery("Livro.pesquisarPorISBN", Livro.class);
		query.setMaxResults(5);
		query.setParameter("isbn", "%" + isbn + "%");
		return query.getResultList();
	}
}