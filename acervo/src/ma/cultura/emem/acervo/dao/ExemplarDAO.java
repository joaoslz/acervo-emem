package ma.cultura.emem.acervo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.acervo.modelo.Emprestimo;
import ma.cultura.emem.acervo.modelo.Exemplar;
import ma.cultura.emem.acervo.modelo.ItemAcervo;

import org.apache.log4j.Logger;

public class ExemplarDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	@Inject
	private DAO<Exemplar> dao;
	
	public int countExemplaresDisponiveis(ItemAcervo it){
		TypedQuery<Long> query = em.createNamedQuery("Exemplar.countExemplaresDisponiveisByItemAcervo", Long.class);
		query.setParameter("idItemAcervo", it.getId());
		return query.getSingleResult().intValue();
	}

	public int countExemplares(ItemAcervo it){
		TypedQuery<Long> query = em.createNamedQuery("Exemplar.countExemplaresByItemAcervo", Long.class);
		query.setParameter("idItemAcervo", it.getId());
		return query.getSingleResult().intValue();
	}

	public Exemplar adicionar(Exemplar t) {
		return dao.adicionar(t);
	}

	public void adicionarLote(List<Exemplar> lote) {
		dao.adicionarLote(lote);
	}

	public Exemplar atualizar(Exemplar t) {
		return dao.atualizar(t);
	}

	public void remover(Exemplar t) {
		dao.remover(t);
	}

	public List<Exemplar> findById(String id) {
		return dao.findById(id);
	}

	public List<Exemplar> findByNome(String nome) {
		return dao.findByNome(nome);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public List<Exemplar> getByNome(String nome) {
		return dao.getByNome(nome);
	}

	public List<Exemplar> findByProperty(String property, Object value) {
		return dao.findByProperty(property, value);
	}

	public PaginatedResult<Exemplar> findByPropertyAndPaginate(String property, Object value, int firstResult, int maxResultsByPage) {
		return dao.findByPropertyAndPaginate(property, value, firstResult, maxResultsByPage);
	}

	public List<Exemplar> findAll() {
		return dao.findAll();
	}

	public List<Exemplar> findAll(int maxResult) {
		return dao.findAll(maxResult);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public PaginatedResult<Exemplar> findAllAndPaginate(int firstResult, int maxResultsByPage) {
		return dao.findAllAndPaginate(firstResult, maxResultsByPage);
	}

	public PaginatedResult<Exemplar> findFilteredAndPaginate(Map<String, Object> filters, int firstResult, int maxResultsByPage) {
		return dao.findFilteredAndPaginate(filters, firstResult, maxResultsByPage);
	}

	public Exemplar buscarPorId(Object id) {
		return dao.buscarPorId(id);
	}

	public int contarTodos() {
		return dao.contarTodos();
	}

	public String toString() {
		return dao.toString();
	}
}