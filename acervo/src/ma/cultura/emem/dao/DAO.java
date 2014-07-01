package ma.cultura.emem.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

import ma.cultura.emem.modelo.Obra;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
		t = em.merge(t);
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
	
	/**
	 * Este método funciona com ilike para atributos do tipo string e equals para atributos numéricos.
	 * @param firstResult
	 * @param maxResultsByPage
	 * @param filters
	 * @return
	 */
	public List<T> findFilteredWithLike(Map<String, Object> filters){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		for(Map.Entry<String, Object> e: filters.entrySet()){
			logger.debug("add Filtro: " + e.getKey() + " like " + e.getValue());
			if(e.getValue() instanceof Short || e.getValue() instanceof Integer|| e.getValue() instanceof Long 
					|| e.getValue() instanceof Float || e.getValue() instanceof Double) {
				criteria = criteria.add( Restrictions.eq(e.getKey(), e.getValue()));				
			}else if(e.getValue() instanceof String){
				criteria = criteria.add( Restrictions.ilike(e.getKey(), e.getValue().toString(), MatchMode.ANYWHERE) );
			}
			//TODO Pode ser feito também para filtro com listas, usando operador in.
		}
		return criteria.list();
	}

	public T buscarPorId(Object id) {
		return em.find(classe, id);
	}

	public int contarTodos() {
		String hql = "select count(n) from " + classe.getSimpleName() + " n";
		return Integer.parseInt(em.createQuery(hql).getSingleResult().toString());
	}
}