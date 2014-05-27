package ma.cultura.emem.dao;


import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.ItemAcervo;

import org.apache.log4j.Logger;

public class ItemAcervoDAO extends DAO<ItemAcervo> {
	private static final long serialVersionUID = -2807083787187739746L;
	@Inject
	private Logger logger;
	
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