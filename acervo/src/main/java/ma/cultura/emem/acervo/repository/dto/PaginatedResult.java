package ma.cultura.emem.acervo.repository.dto;

import java.util.List;

public class PaginatedResult<T> {
	
	private int countAll;
	private List<T> lista;
	
	public PaginatedResult(int countAll, List<T> resultPage) {
		super();
		this.countAll = countAll;
		this.lista = resultPage;
	}
	
	public int getCountAll() {
		return countAll;
	}
	
	public List<T> getLista() {
		return lista;
	}
}
