package ma.cultura.emem.acervo.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.dao.filtro.ObraFilter;
import ma.cultura.emem.acervo.modelo.Obra;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PesquisaObraDAO implements Serializable {

	private static final long serialVersionUID = -451616595796258543L;

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Obra> filtradas(ObraFilter filtro) {
		Session session = this.em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Obra.class);

		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do Título da Obra
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getSubtitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do subtítulo da Obra
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}

		if (filtro.getAnoInicio() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a filtro.anoInicio
			criteria.add(Restrictions.ge("ano", filtro.getAnoInicio()));
		}

		if (filtro.getAnoFim() != null) {
			// id deve ser menor ou igual (le = lower or equal) a filtro.anoFim
			criteria.add(Restrictions.le("ano", filtro.getAnoFim()));
		}

		if (filtro.getTiposObra() != null && !(filtro.getTiposObra().isEmpty())) {
			// adicionamos uma restrição "in", passando uma lista de tipoObra
			criteria.add(Restrictions.in("tipoObra", filtro.getTiposObra()));
		}

		return criteria.addOrder(Order.asc("titulo")).list();
	}
}