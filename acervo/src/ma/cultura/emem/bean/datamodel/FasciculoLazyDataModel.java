package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.FasciculoDAO;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.Periodico;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class FasciculoLazyDataModel extends LazyDataModel<Fasciculo> {

	private static final long serialVersionUID = -8766651202218907745L;

	@Inject
	private FasciculoDAO fasciculoDAO;
	
	private Periodico periodico;

	public FasciculoLazyDataModel() {
	}

	@Override
	public Fasciculo getRowData(String rowKey) {
		return fasciculoDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Fasciculo f) {
		return f.getId();
	}
	
	@Override
	public List<Fasciculo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		setRowCount(fasciculoDAO.contarTodos());
		return fasciculoDAO.findByPeriodicoPaginated(periodico, first, pageSize);
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}
}
