package ma.cultura.emem.acervo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.acervo.modelo.Emprestimo;
import ma.cultura.emem.acervo.modelo.Exemplar;

import org.apache.log4j.Logger;

public class EmprestimoDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	@Inject
	private DAO<Emprestimo> dao;
	
	public Emprestimo findUltimoEmprestimoEmAberto(Exemplar ex){
		String jpql = "from Emprestimo e where e.dataDevolucao is null and e.exemplar.id = :idExemplar order by e.id desc";
		TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
		query.setParameter("idExemplar", ex.getId());
		List<Emprestimo> list = query.getResultList();
		if(list != null && list.size() > 0)
			return query.getResultList().get(0);
		else 
			return null;
	}

	public Emprestimo adicionar(Emprestimo t) {
		return dao.adicionar(t);
	}

	public void adicionarLote(List<Emprestimo> lote) {
		dao.adicionarLote(lote);
	}

	public Emprestimo atualizar(Emprestimo t) {
		return dao.atualizar(t);
	}

	public void remover(Emprestimo t) {
		dao.remover(t);
	}

	public List<Emprestimo> findById(String id) {
		return dao.findById(id);
	}

	public List<Emprestimo> findByNome(String nome) {
		return dao.findByNome(nome);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Emprestimo> getByNome(String nome) {
		return dao.getByNome(nome);
	}

	public List<Emprestimo> findByProperty(String property, Object value) {
		return dao.findByProperty(property, value);
	}

	public PaginatedResult<Emprestimo> findByPropertyAndPaginate(String property, Object value, int firstResult, int maxResultsByPage) {
		return dao.findByPropertyAndPaginate(property, value, firstResult, maxResultsByPage);
	}

	public List<Emprestimo> findAll() {
		return dao.findAll();
	}

	public List<Emprestimo> findAll(int maxResult) {
		return dao.findAll(maxResult);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public PaginatedResult<Emprestimo> findAllAndPaginate(int firstResult, int maxResultsByPage) {
		return dao.findAllAndPaginate(firstResult, maxResultsByPage);
	}

	public PaginatedResult<Emprestimo> findFilteredAndPaginate(Map<String, Object> filters, int firstResult, int maxResultsByPage) {
		return dao.findFilteredAndPaginate(filters, firstResult, maxResultsByPage);
	}

	public Emprestimo buscarPorId(Object id) {
		return dao.buscarPorId(id);
	}

	public int contarTodos() {
		return dao.contarTodos();
	}

	public String toString() {
		return dao.toString();
	}

	public DAO<Emprestimo> getDao() {
		return dao;
	}
	
	
}
