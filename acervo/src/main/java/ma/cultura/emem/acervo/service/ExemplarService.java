package ma.cultura.emem.acervo.service;

import java.io.Serializable;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.ItemAcervo;
import ma.cultura.emem.acervo.repository.Exemplares;

public class ExemplarService implements Serializable {
	
	private static final long serialVersionUID = 4092041092242167539L;
	
	@Inject
	private Exemplares repository;
	
	public int countExemplaresDisponiveis(ItemAcervo it) {
		return repository.countExemplaresDisponiveis(it);
	}
	
	public int countExemplares(ItemAcervo it) {
		return repository.countExemplares(it);
	}
}
