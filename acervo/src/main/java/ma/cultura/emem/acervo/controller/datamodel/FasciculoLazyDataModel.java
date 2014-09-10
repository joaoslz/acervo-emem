package ma.cultura.emem.acervo.controller.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.model.auxiliar.Periodico;
import ma.cultura.emem.acervo.repository.Consultas;
import ma.cultura.emem.acervo.repository.dto.PaginatedResult;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real
 * datasource like a database.
 */
public class FasciculoLazyDataModel extends LazyDataModel<Fasciculo> {
	
	private static final long serialVersionUID = -8766651202218907745L;
	
	@Inject
	private transient Logger logger;
	// FIXME Não consegui injetar o service.
	@Inject
	private Consultas<Fasciculo> service;
	
	private Periodico periodico;
	
	@Override
	public Fasciculo getRowData(String rowKey) {
		return service.findById(Integer.valueOf(rowKey));
	}
	
	@Override
	public Object getRowKey(Fasciculo f) {
		return f.getId();
	}
	
	@Override
	public List<Fasciculo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		if (periodico != null) {
			PaginatedResult<Fasciculo> p;
			if (filters != null && filters.size() > 0) {
				logger.debug("Usando Filtros ");
				filters.put("periodico.id", periodico.getId());
				p = service.findFilteredAndPaginate(filters, first, pageSize);
			} else {
				logger.debug("Sem Filtros ");
				p = service.findByPropertyAndPaginate("periodico.id", periodico.getId(), first, pageSize);
			}
			setRowCount(p.getCountAll());
			return p.getLista();
		} else {
			
			return null;
		}
	}
	
	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}
}