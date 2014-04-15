package ma.cultura.emem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Livro;

public class LivroDAO extends DAO<Livro> {

	public LivroDAO() {
		super(Livro.class);
	}

	public Livro buscarLivroFetchExemplares(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Livro> query = em
				.createQuery(
						"from Livro l left join fetch l.exemplares e where livro.id = :id",
						Livro.class);
		query.setParameter("id", id);
		Livro livro = query.getSingleResult();
		em.close();
		return livro;
	}

	public Livro merge(Livro l) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		// FIXME recupera novamente os autores do banco para que o JPA possa
		// reconhece-los como entidades gerenciaveis.
		List<Autor> autores = new ArrayList<Autor>();
		if (l.getAutores() != null && l.getAutores().size() > 0) {
			for (Autor autor : l.getAutores()) {
				autor = em.find(Autor.class, autor.getId());
				autores.add(autor);
			}
		}
		l.setAutores(autores);
		// FIXME recupera novamente os assuntos do banco para que o JPA possa
		// reconhece-los como entidades gerenciaveis.
		List<Assunto> assuntos = new ArrayList<Assunto>();
		if (l.getAssuntos() != null && l.getAssuntos().size() > 0) {
			for (Assunto assunto : l.getAssuntos()) {
				assunto = em.find(Assunto.class, assunto.getId());
				assuntos.add(assunto);
			}
		}
		l.setAssuntos(assuntos);

		l = em.merge(l);
		em.getTransaction().commit();
		em.close();
		return l;
	}

	public List<Livro> listarLivros() {

		String consulta = "select distinct l from Livro l left join fetch l.autores order by l.id desc";

		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Livro> query = em.createQuery(consulta, Livro.class);
		query.setMaxResults(5);
		List<Livro> listaObras = query.getResultList();

		em.close();

		return listaObras;
	}

	public List<Livro> likeByISBN(String isbn) {

		String consulta = "select distinct l from Livro l left join fetch l.autores where l.isbn like :isbn order by l.id desc";

		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Livro> query = em.createQuery(consulta, Livro.class);
		query.setMaxResults(5);
		query.setParameter("isbn", "%" + isbn + "%");
		List<Livro> listaObras = query.getResultList();

		em.close();

		return listaObras;
	}

	public List<Livro> likeByTitulo(String titulo) {

		String consulta = "select distinct l from Livro l left join fetch l.autores where l.titulo like :titulo order by l.id desc";

		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Livro> query = em.createQuery(consulta, Livro.class);
		query.setMaxResults(5);
		query.setParameter("titulo", "%" + titulo + "%");
		List<Livro> listaObras = query.getResultList();

		em.close();

		return listaObras;
	}
}
