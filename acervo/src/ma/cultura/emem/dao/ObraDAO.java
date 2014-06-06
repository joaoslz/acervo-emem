package ma.cultura.emem.dao;


import java.util.List;

import ma.cultura.emem.dao.filtro.ObraFilter;
import ma.cultura.emem.modelo.Obra;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ObraDAO extends DAO<Obra> {
	private static final long serialVersionUID = -2807083787187739746L;

	public ObraDAO() {
		super(Obra.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Obra> filtradas(ObraFilter filtro) {
		Session session = this.em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Obra.class);
				
		if (filtro.getTitulo() != null) {
			// titulo pode ser uma substring que aparece em qualquer parte do Título da Obra 
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}

		if (filtro.getSubtitulo() != null) {
			// titulo pode ser uma substring que aparece em qualquer parte do Título da Obra 
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("titulo")).list();
	}
	
	
}