package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class ObraLazyDataModel extends LazyDataModel<Obra> {

	private static final long serialVersionUID = -6844382640440410455L;
	@Inject
	private ObraDAO obraDAO;
	@Inject
	private Logger logger;

	public ObraLazyDataModel() {
	}

	@Override
	public Obra getRowData(String rowKey) {
		return obraDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Obra o) {
		return o.getId();
	}

	@Override
	public List<Obra> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<Obra> result = null;
		if(filters != null && filters.size() > 0){
			logger.debug("Usando Filtros ");
			result = obraDAO.findFilteredWithLike(filters);
			setRowCount(result.size());
		}else{
			logger.debug("Sem Filtros ");
			result = obraDAO.findAllPaginated(first, pageSize);
			setRowCount(obraDAO.contarTodos());
		}
		return result;
	}	
}
