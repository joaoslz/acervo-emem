package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.dao.PaginatedResult;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.auxiliar.Periodico;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class FasciculoLazyDataModel extends LazyDataModel<Fasciculo> {

	private static final long serialVersionUID = -8766651202218907745L;

	private Logger logger = Logger.getLogger(getClass());
	private DAO<Fasciculo> fasciculoDAO;
	private Periodico periodico;

	public FasciculoLazyDataModel(DAO<Fasciculo> fasciculoDAO) {
		super();
		this.fasciculoDAO = fasciculoDAO;
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
		if (periodico != null) {
			PaginatedResult<Fasciculo> p;
			if (filters != null && filters.size() > 0) {
				logger.debug("Usando Filtros ");
				filters.put("periodico.id", periodico.getId());
				p = fasciculoDAO.findFilteredAndPaginate(filters, first, pageSize);
			} else {
				logger.debug("Sem Filtros ");
				p = fasciculoDAO.findByPropertyAndPaginate("periodico.id", periodico.getId(), first, pageSize);
			}
			setRowCount(p.getCountAll());
			return p.getLista();
		}else{
			
			return null;			
		}
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}
}
