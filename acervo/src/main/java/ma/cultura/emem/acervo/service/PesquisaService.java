package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.CD;
import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.repository.Pesquisas;
import ma.cultura.emem.acervo.repository.dto.CDFilter;
import ma.cultura.emem.acervo.repository.dto.ObraFilter;

public class PesquisaService implements Serializable {
	
	private static final long serialVersionUID = 103622249416771602L;
	@Inject
	private Pesquisas pesquisas;
	
	public List<Obra> filtrarObras(ObraFilter filtro) {
		return pesquisas.filtrarObras(filtro);
	}

	public List<CD> filtrarCDs(CDFilter filtro) {
		return pesquisas.filtrarCDs(filtro);
	}
}