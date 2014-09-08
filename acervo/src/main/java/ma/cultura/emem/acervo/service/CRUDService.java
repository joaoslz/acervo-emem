package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.acervo.repository.CRUDRepository;
import ma.cultura.emem.acervo.util.jpa.Transactional;

public class CRUDService implements Serializable {
	
	private static final long serialVersionUID = 8820257464842703051L;
	
	@Inject
	private CRUDRepository repository;
	
	@Transactional
	public void adicionar(Object t) {
		repository.adicionar(t);
	}
	
	@Transactional
	public void adicionarLote(List<Object> lote) {
		repository.adicionarLote(lote);
	}
	
	@Transactional
	public Object atualizar(Object t) {
		return repository.atualizar(t);
	}
	
	@Transactional
	public void remover(Object t) {
		repository.remover(t);
	}
}