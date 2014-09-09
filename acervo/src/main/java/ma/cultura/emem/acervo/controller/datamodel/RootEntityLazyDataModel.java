package ma.cultura.emem.acervo.controller.datamodel;

import java.util.List;
import java.util.Map;

import ma.cultura.emem.acervo.model.RootEntity;
import ma.cultura.emem.acervo.repository.dto.PaginatedResult;
import ma.cultura.emem.acervo.service.ConsultasService;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class RootEntityLazyDataModel<T extends RootEntity> extends LazyDataModel<T> {
	
	private static final long serialVersionUID = 1764206779658823028L;
	
	private transient Logger logger = Logger.getLogger(getClass());
	private ConsultasService<T> service;
	
	public RootEntityLazyDataModel(ConsultasService<T> service) {
		this.service = service;
	}
	
	@Override
	public T getRowData(String rowKey) {
		logger.debug("buscando por id: " + rowKey);
		return service.findById(Integer.valueOf(rowKey));
	}
	
	@Override
	public Object getRowKey(RootEntity t) {
		return t.getId();
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		PaginatedResult<T> p;
		if (filters != null && filters.size() > 0) {
			logger.debug("Usando Filtros ");
			p = service.findFilteredAndPaginate(filters, first, pageSize);
		} else {
			logger.debug("Sem Filtros ");
			p = service.findAllAndPaginate(first, pageSize);
		}
		setRowCount(p.getCountAll());
		return p.getLista();
	}
}