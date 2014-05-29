package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.modelo.Obra;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class ObraLazyDataModel extends LazyDataModel<Obra> {

	private static final long serialVersionUID = -6844382640440410455L;
	@Inject
	private ObraDAO obraDAO;

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
	public List<Obra> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		setRowCount(obraDAO.contarTodos());
		return obraDAO.findAllPaginated(first, pageSize);
	}
}
