package ma.cultura.emem.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.ItemAcervo;

public class ItemAcervoDAO extends DAO<ItemAcervo> {
	private static final long serialVersionUID = -2807083787187739746L;
	
	public ItemAcervoDAO() {
		super(ItemAcervo.class);
	}
	
	public List<ItemAcervo> findByTitulo(String titulo) {
		logger.debug("Pesquisando ItemAcervo por título: " + titulo);
		TypedQuery<ItemAcervo> query = em.createNamedQuery("ItemAcervo.findByTitulo", ItemAcervo.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList();
	}
}