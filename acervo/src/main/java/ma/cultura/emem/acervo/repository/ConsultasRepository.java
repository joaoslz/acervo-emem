package ma.cultura.emem.acervo.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.acervo.repository.dto.PaginatedResult;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ConsultasRepository<T> implements Serializable{

	private static final long serialVersionUID = 3070442360970860184L;
	
	private EntityManager em;	
	private Logger logger;	
	private String entityName;
	private Class<T> classe;

	public ConsultasRepository(Class<T> classe, EntityManager em) {
		logger = Logger.getLogger(getClass());
		entityName = classe.getSimpleName();
		this.classe = classe;
		this.em = em;
		logger.debug(" -->> Repositório criado para " + classe);
	}

	/**
	 * Via NamedQuery, para melhor desempenho.
	 * @param nome
	 * @return
	 */
	public List<T> findByNomeLike(String nome) {
		return findByNome("%"+nome+"%");
	}

	/**
	 * Via NamedQuery, para melhor desempenho.
	 * @param nome
	 * @return
	 */
	public List<T> findByNome(String nome) {
		try{
			TypedQuery<T> query = em.createNamedQuery(entityName+".findByNome", classe);
			query.setParameter("nome", nome);
			return query.getResultList();
		}catch(IllegalArgumentException exc){
			logger.error("NamedQuery não existe para esta entidade", exc);
		}
		return new ArrayList<T>();
	}	
	
//.................................................................

	public T findById(Object id) {
		return em.find(classe, id);
	}

	public List<T> findByIdLike(String id){
		TypedQuery<T> query = em.createQuery("from " + entityName +" t where CAST(t.id as string) like :id", classe);
		query.setParameter("id", "%"+id+"%");
		return query.getResultList();		
	}
	
	public int countAll() {
		String hql = "select count(n) from " + entityName + " n";
		return Integer.parseInt(em.createQuery(hql).getSingleResult().toString());
	}
	
	public List<T> findByProperty(String property, Object value){
		Criteria criteria = em.unwrap(Session.class).createCriteria(classe);
		criteria = criteria.add(Restrictions.eq(property, value));	
		return criteria.list();
	}

	public List<T> findByPropertyLike(String property, String value){
		Criteria criteria = em.unwrap(Session.class).createCriteria(classe);
		criteria = criteria.add(Restrictions.like(property, value, MatchMode.ANYWHERE));	
		return criteria.list();
	}
	
	public PaginatedResult<T> findByPropertyAndPaginate(String property, Object value, int firstResult, int maxResultsByPage){
		Criteria criteria = em.unwrap(Session.class).createCriteria(classe);
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
		TypedQuery<T> q = em.createNamedQuery(entityName + ".findAll", classe);
		return q.getResultList();
	}

	public List<T> findAll(int maxResult) {
		logger.debug("findAll com max result: " + maxResult);
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		List<T> lista = em.createNamedQuery(entityName + ".findAll", classe).setMaxResults(maxResult).getResultList();
		return lista;
	}

	public PaginatedResult<T>  findAllAndPaginate(int firstResult, int maxResultsByPage) {
		logger.debug("utilizando findAll com lista paginada...");
		// IMPORTANTE:Cada entidade deve implementar a named query Entidade.findAll 
		TypedQuery<T> q = em.createNamedQuery(entityName + ".findAll", classe);
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
	@SuppressWarnings("unchecked")
	public PaginatedResult<T> findFilteredAndPaginate(Map<String, Object> filters, int firstResult, int maxResultsByPage){
		Criteria criteria = em.unwrap(Session.class).createCriteria(classe);
		
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
}