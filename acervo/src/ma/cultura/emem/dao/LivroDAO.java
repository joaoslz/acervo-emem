package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Livro;

import org.apache.log4j.Logger;

public class LivroDAO extends DAO<Livro> {
	
	private static final long serialVersionUID = -3418373953576656728L;
	private static final Logger LOGGER = Logger.getLogger(LivroDAO.class);

	public LivroDAO() {
		super(Livro.class);
	}

//	@Override
//	public Livro atualizar(Livro l) {
//		em.getTransaction().begin();
//		// FIXME recupera novamente os autores do banco para que o JPA possa
//		// reconhece-los como entidades gerenciaveis.
////		List<Autor> autores = new ArrayList<Autor>();
////		if (l.getAutores() != null && l.getAutores().size() > 0) {
////			for (Autor autor : l.getAutores()) {
////				autor = em.find(Autor.class, autor.getId());
////				autores.add(autor);
////			}
////		}
////		l.setAutores(autores);
////		// FIXME recupera novamente os assuntos do banco para que o JPA possa
////		// reconhece-los como entidades gerenciaveis.
////		List<Assunto> assuntos = new ArrayList<Assunto>();
////		if (l.getAssuntos() != null && l.getAssuntos().size() > 0) {
////			for (Assunto assunto : l.getAssuntos()) {
////				assunto = em.find(Assunto.class, assunto.getId());
////				assuntos.add(assunto);
////			}
////		}
////		l.setAssuntos(assuntos);
//
//		l = em.merge(l);
//		em.getTransaction().commit();
//		LOGGER.debug("Atualizando dados do livro: " + l.getTitulo());
//		return l;
//	}


	public List<Livro> pesquisarPorISBN(String isbn) {
		TypedQuery<Livro> query = em.createNamedQuery("Livro.pesquisarPorISBN", Livro.class);
		query.setMaxResults(5);
		query.setParameter("isbn", "%" + isbn + "%");
		return query.getResultList();
	}
}