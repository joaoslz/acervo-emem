package ma.cultura.emem.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = -4361432740747336731L;

	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	private final Class<T> classe;

	@Inject
	protected EntityManager em;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public T adicionar(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}

	public T atualizar(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	public void remover(T t) {
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
	}

	public List<T> listarTodos() {
		// IMPORTANTE:Cada entidade deve implementar a named query listarTodos 
		// com um atributo estatico NAMED_QUERY_LISTAR_TODOS para o nome da query.
		List<T> lista = null;
		try {
			// pega o valor do atributo NAMED_QUERY_LISTAR_TODOS.
			String namedQuery = classe.getField("NAMED_QUERY_LISTAR_TODOS").get(null).toString();
			lista = em.createNamedQuery(namedQuery, classe).getResultList();
		} catch (Exception e) {
			LOGGER.error("Erro ao criar named query NAMED_QUERY_LISTAR_TODOS para a classe" + classe.getName(), e);
		}
		return lista;
	}

	public List<T> listarTodos(int maxResult) {
		// IMPORTANTE:Cada entidade deve implementar a named query listarTodos 
		// com um atributo estatico NAMED_QUERY_LISTAR_TODOS para o nome da query.
		List<T> lista = null;
		try {
			// pega o valor do atributo NAMED_QUERY_LISTAR_TODOS.
			String namedQuery = classe.getField("NAMED_QUERY_LISTAR_TODOS").get(null).toString();
			lista = em.createNamedQuery(namedQuery, classe).setMaxResults(maxResult).getResultList();
			LOGGER.debug("lista com max result: " + maxResult);
		} catch (Exception e) {
			LOGGER.error("Erro ao criar named query NAMED_QUERY_LISTAR_TODOS para a classe" + classe.getName(), e);
		}
		return lista;
	}

	public List<T> listarPorPagina(int firstResult, int maxResultsByPage) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResultsByPage).getResultList();
		LOGGER.debug("utilizando lista paginada...");
		return lista;
	}

	public T buscarPorId(Object id) {
		return em.find(classe, id);
	}

	public int contarTodos() {
		String hql = "select count(n) from " + classe.getSimpleName() + " n";
		return Integer.parseInt(em.createQuery(hql).getSingleResult().toString());
	}
}