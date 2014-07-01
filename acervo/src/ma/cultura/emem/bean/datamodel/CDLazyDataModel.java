package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.CDDAO;
import ma.cultura.emem.modelo.CD;
import ma.cultura.emem.modelo.Partitura;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class CDLazyDataModel extends LazyDataModel<CD> {

	private static final long serialVersionUID = 1764206779658823028L;
	
	@Inject
	private CDDAO cdDAO;
	@Inject
	private Logger logger;

	public CDLazyDataModel() {
	}

	@Override
	public CD getRowData(String rowKey) {
		return cdDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(CD cd) {
		return cd.getId();
	}

	@Override
	public List<CD> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<CD> result = null;
		if(filters != null && filters.size() > 0){
			logger.debug("Usando Filtros ");
			result = cdDAO.findFilteredWithLike(filters);
			setRowCount(result.size());
		}else{
			logger.debug("Sem Filtros ");
			result = cdDAO.findAllPaginated(first, pageSize);
			setRowCount(cdDAO.contarTodos());
		}
		return result;
	}
}