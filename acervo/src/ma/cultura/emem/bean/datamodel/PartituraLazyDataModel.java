package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.PartituraDAO;
import ma.cultura.emem.modelo.Partitura;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class PartituraLazyDataModel extends LazyDataModel<Partitura> {

	private static final long serialVersionUID = 1132999011331958579L;
	@Inject
	private PartituraDAO partituraDAO;

	public PartituraLazyDataModel() {
	}

	@Override
	public Partitura getRowData(String rowKey) {
		return partituraDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Partitura p) {
		return p.getId();
	}
	
	@Override
	public List<Partitura> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		setRowCount(partituraDAO.contarTodos());
		return partituraDAO.listarPorPagina(first, pageSize);
	}
}
