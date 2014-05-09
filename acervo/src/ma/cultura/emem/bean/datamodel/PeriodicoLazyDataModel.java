package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.PeriodicoDAO;
import ma.cultura.emem.modelo.Periodico;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class PeriodicoLazyDataModel extends LazyDataModel<Periodico> {

	private static final long serialVersionUID = -6844382640440410455L;
	@Inject
	private PeriodicoDAO periodicoDAO;

	public PeriodicoLazyDataModel() {
	}

	@Override
	public Periodico getRowData(String rowKey) {
		return periodicoDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Periodico p) {
		return p.getId();
	}
	
	@Override
	public List<Periodico> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		setRowCount(periodicoDAO.contarTodos());
		return periodicoDAO.listarPorPagina(first, pageSize);
	}
}
