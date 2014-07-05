package ma.cultura.emem.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = -4361432740747336731L;

	protected Logger logger;

	private final Class<T> classe;

	private EntityManager em;

	public DAO(Class<T> classe, EntityManager em) {
		logger = Logger.getLogger(getClass());
		this.classe = classe;
		this.em = em;
		logger.debug("...::::" + classe);
	}

	public T adicionar(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}
	
	public void adicionarLote(List<T> lote) {
		em.getTransaction().begin();
		logger.debug("Quantidade : " + lote.size());
		for (T t: lote) {
			em.persist(t);
		}
		em.getTransaction().commit();
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

	/**
	 * Via NamedQuery, para melhor desempenho.
	 * FIXME Não funciona para entidades que não possui o campo <b>nome</b>
	 * @param nome
	 * @return
	 */
	public List<T> findByNome(String nome) {
		return getByNome("%"+nome+"%");
	}

	public List<T> getByNome(String nome) {
		try{
			logger.debug("findByNome para entidade " + classe.getSimpleName());
			TypedQuery<T> query = em.createNamedQuery(classe.getSimpleName()+".findByNome", classe);
			query.setParameter("nome", nome);
			return query.getResultList();
		}catch(IllegalArgumentException exc){
			logger.error("NamedQuery não existe para esta entidade", exc);
		}
		return new ArrayList<T>();
	}
	
	public List<T> findByProperty(String property, Object value){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		criteria = criteria.add(Restrictions.eq(property, value));	
		return criteria.list();
	}
	
	public PaginatedResult<T> findByPropertyAndPaginate(String property, Object value, int firstResult, int maxResultsByPage){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		criteria = criteria.add(Restrictions.eq(property, value));	
		int count = criteria.list().size();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResultsByPage);
		List<T> lista = criteria.list();
		PaginatedResult<T> result = new PaginatedResult<>(count, lista);
		return result;
		
	}

	public List<T> findAll() {
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = null;
		String namedQuery = classe.getSimpleName() + ".findAll";
		TypedQuery<T> q = em.createNamedQuery(namedQuery, classe);
		return q.getResultList();
	}

	public List<T> findAll(int maxResult) {
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = null;
		String namedQuery = classe.getSimpleName() + ".findAll";
		lista = em.createNamedQuery(namedQuery, classe).setMaxResults(maxResult).getResultList();
		logger.debug("lista com max result: " + maxResult);
		return lista;
	}

	public PaginatedResult<T>  findAllAndPaginate(int firstResult, int maxResultsByPage) {
		logger.debug("utilizando lista paginada...");
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		String namedQuery = classe.getSimpleName() + ".findAll";
		TypedQuery<T> q = em.createNamedQuery(namedQuery, classe);
		int count = q.getResultList().size();
		List<T> lista = q.setFirstResult(firstResult).setMaxResults(maxResultsByPage).getResultList();
		PaginatedResult<T> result = new PaginatedResult<>(count, lista);
		return result;
	}
	
	/**
	 * Este método funciona com ilike para atributos do tipo string e equals para atributos numéricos.
	 * @param firstResult
	 * @param maxResultsByPage
	 * @param filters
	 * @return
	 */
	public PaginatedResult<T> findFilteredAndPaginate(Map<String, Object> filters, int firstResult, int maxResultsByPage){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		for(Map.Entry<String, Object> e: filters.entrySet()){
			if(e.getValue() instanceof Short || e.getValue() instanceof Integer|| e.getValue() instanceof Long 
					|| e.getValue() instanceof Float || e.getValue() instanceof Double) {
				criteria = criteria.add( Restrictions.eq(e.getKey(), e.getValue()));				
			}else if(e.getValue() instanceof String){
				logger.debug("add Filtro: " + e.getKey() + " LIKE %"+e.getValue().toString()+"%");
				criteria = criteria.add( Restrictions.ilike(e.getKey(), "%"+e.getValue().toString()+"%") );
			}
			//TODO Pode ser feito também para filtro com listas, usando operador in.
		}
		int count = criteria.list().size();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResultsByPage);
		List<T> lista = criteria.list();
		PaginatedResult<T> result = new PaginatedResult<>(count, lista);
		return result;
	}

	public T buscarPorId(Object id) {
		return em.find(classe, id);
	}

	public int contarTodos() {
		String hql = "select count(n) from " + classe.getSimpleName() + " n";
		return Integer.parseInt(em.createQuery(hql).getSingleResult().toString());
	}
}