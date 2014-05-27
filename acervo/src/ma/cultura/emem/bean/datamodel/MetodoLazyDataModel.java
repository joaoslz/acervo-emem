package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.MetodoDAO;
import ma.cultura.emem.modelo.Metodo;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class MetodoLazyDataModel extends LazyDataModel<Metodo> {

	private static final long serialVersionUID = -6844382640440410455L;
	@Inject
	private MetodoDAO metodoDAO;

	public MetodoLazyDataModel() {
	}

	@Override
	public Metodo getRowData(String rowKey) {
		return metodoDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Metodo m) {
		return m.getId();
	}
	
	@Override
	public List<Metodo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		setRowCount(metodoDAO.contarTodos());
		return metodoDAO.findAllPaginated(first, pageSize);
	}
}
