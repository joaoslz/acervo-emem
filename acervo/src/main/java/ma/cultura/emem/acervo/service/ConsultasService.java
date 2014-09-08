package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ma.cultura.emem.acervo.repository.ConsultasRepository;
import ma.cultura.emem.acervo.repository.dto.PaginatedResult;

public class ConsultasService<T> implements Serializable {
	
	private static final long serialVersionUID = 8820257464842703051L;
	
	private ConsultasRepository<T> repository;
	
	public ConsultasService(ConsultasRepository<T> repository) {
		this.repository = repository;
	}
	
	public List<T> findByNomeLike(String nome) {
		return repository.findByNomeLike(nome);
	}
	
	public List<T> findByNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public T findById(Object id) {
		return repository.findById(id);
	}
	
	public List<T> findByIdLike(String id) {
		return repository.findByIdLike(id);
	}
	
	public int countAll() {
		return repository.countAll();
	}
	
	public List<T> findByProperty(String property, Object value) {
		return repository.findByProperty(property, value);
	}
	
	public List<T> findByPropertyLike(String property, String value) {
		return repository.findByPropertyLike(property, value);
	}
	
	public PaginatedResult<T> findByPropertyAndPaginate(String property, Object value, int firstResult, int maxResultsByPage) {
		return repository.findByPropertyAndPaginate(property, value, firstResult, maxResultsByPage);
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}
	
	public List<T> findAll(int maxResult) {
		return repository.findAll(maxResult);
	}
	
	public PaginatedResult<T> findAllAndPaginate(int firstResult, int maxResultsByPage) {
		return repository.findAllAndPaginate(firstResult, maxResultsByPage);
	}
	
	public PaginatedResult<T> findFilteredAndPaginate(Map<String, Object> filters, int firstResult, int maxResultsByPage) {
		return repository.findFilteredAndPaginate(filters, firstResult, maxResultsByPage);
	}
}