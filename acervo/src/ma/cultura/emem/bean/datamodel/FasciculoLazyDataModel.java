package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.FasciculoDAO;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.auxiliar.Periodico;

import org.apache.log4j.Logger;
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
	@Inject
	private Logger logger;
	
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
		List<Fasciculo> result = null;
		
		if(periodico != null){
			if(filters != null && filters.size() > 0){
				logger.debug("Usando Filtros ");
				filters.put("periodico.id", periodico.getId());
				result = fasciculoDAO.findFilteredWithLike(filters);
				setRowCount(result.size());
			}else{
				logger.debug("Sem Filtros ");
				setRowCount(fasciculoDAO.contarTodosByPeriodico(periodico));
				result = fasciculoDAO.findByPeriodicoPaginated(periodico, first, pageSize);
			}
		}
		return result;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}
}
