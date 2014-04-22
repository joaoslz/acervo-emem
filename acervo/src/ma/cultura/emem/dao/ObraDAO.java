package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Obra;

public class ObraDAO extends DAO<Obra> {

	private static final long serialVersionUID = 4643738850166344000L;

	
	public ObraDAO() {
		super(Obra.class);
	}

	public List<Obra> pesquisarPorTitulo(String titulo) {
		TypedQuery<Obra> query = em.createNamedQuery(Obra.NAMED_QUERY_PESQUISAR_POR_TITULO, Obra.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList();
	}
}
