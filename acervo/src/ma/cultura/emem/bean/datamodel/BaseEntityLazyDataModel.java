package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.BaseEntity;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class BaseEntityLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1764206779658823028L;

	private Logger logger = Logger.getLogger(getClass());
	
	private DAO<T> dao;

	public BaseEntityLazyDataModel(DAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public T getRowData(String rowKey) {
		logger.debug("buscando por id: " + rowKey);
		return dao.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(BaseEntity t) {
		return t.getId();
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<T> result = null;
		if(filters != null && filters.size() > 0){
			logger.debug("Usando Filtros ");
			result = dao.findFilteredWithLike(filters);
			setRowCount(result.size());
		}else{
			logger.debug("Sem Filtros ");
			result = dao.findAllPaginated(first, pageSize);
			setRowCount(dao.contarTodos());
		}
		return result;
	}
}