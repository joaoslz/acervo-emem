package ma.cultura.emem.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = -4361432740747336731L;

	@Inject
	protected Logger logger;

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

	public List<T> findAll() {
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = null;
		String namedQuery = classe.getSimpleName() + ".findAll";
		lista = em.createNamedQuery(namedQuery, classe).getResultList();
		return lista;
	}

	public List<T> findAll(int maxResult) {
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = null;
		String namedQuery = classe.getSimpleName() + ".findAll";
		lista = em.createNamedQuery(namedQuery, classe).setMaxResults(maxResult).getResultList();
		logger.debug("lista com max result: " + maxResult);
		return lista;
	}

	public List<T> findAllPaginated(int firstResult, int maxResultsByPage) {
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = null;
		String namedQuery = classe.getSimpleName() + ".findAll";
		lista = em.createNamedQuery(namedQuery, classe).setFirstResult(firstResult).setMaxResults(maxResultsByPage).getResultList();
		logger.debug("utilizando lista paginada...");
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