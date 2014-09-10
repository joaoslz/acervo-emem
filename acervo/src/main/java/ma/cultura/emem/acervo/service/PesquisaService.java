package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.repository.PesquisasObra;
import ma.cultura.emem.acervo.repository.dto.ObraFilter;

public class PesquisaService implements Serializable {
	
	private static final long serialVersionUID = 103622249416771602L;
	@Inject
	private PesquisasObra pesquisasObra;
	
	public List<Obra> filtrarObras(ObraFilter filtro) {
		return pesquisasObra.filtrarObras(filtro);
	}
}
