package ma.cultura.emem.bean.datamodel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.modelo.Livro;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
@Named
public class LivroLazyDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = -6844382640440410455L;
	@Inject
	private LivroDAO livroDAO;

	public LivroLazyDataModel() {
	}

	@Override
	public Livro getRowData(String rowKey) {
		return livroDAO.buscarPorId(Integer.valueOf(rowKey));
	}

	@Override
	public Object getRowKey(Livro livro) {
		return livro.getId();
	}
	
	@Override
	public List<Livro> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		setRowCount(livroDAO.contarTodos());
		return livroDAO.listarPorPagina(first, pageSize);
	}
}
