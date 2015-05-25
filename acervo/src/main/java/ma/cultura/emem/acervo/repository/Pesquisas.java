package ma.cultura.emem.acervo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ma.cultura.emem.acervo.model.CD;
import ma.cultura.emem.acervo.model.Fasciculo;
import ma.cultura.emem.acervo.model.Obra;
import ma.cultura.emem.acervo.model.Partitura;
import ma.cultura.emem.acervo.repository.dto.CDFilter;
import ma.cultura.emem.acervo.repository.dto.FasciculoFilter;
import ma.cultura.emem.acervo.repository.dto.ObraFilter;
import ma.cultura.emem.acervo.repository.dto.PartituraFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Pesquisas implements Serializable {
	
	private static final long serialVersionUID = -451616595796258543L;
	
	@Inject
	private EntityManager em;
	@Inject
	private transient Logger logger;
	
	public List<Obra> filtrarObras(ObraFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Obra.class, "obra");
		
		if (StringUtils.isNotBlank(filtro.getIsbn())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do
			// Título da Obra
			criteria.add(Restrictions.ilike("isbn", filtro.getIsbn(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do
			// Título da Obra
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getSubtitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do
			// subtítulo da Obra
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getAnoInicio() != null && filtro.getAnoInicio() > 0) {
			logger.debug(filtro.getAnoInicio());
			// id deve ser maior ou igual (ge = greater or equals) a
			// filtro.anoInicio
			criteria.add(Restrictions.ge("ano", filtro.getAnoInicio()));
		}
		
		if (filtro.getAnoFim() != null && filtro.getAnoFim() > 0) {
			logger.debug(filtro.getAnoFim());
			// id deve ser menor ou igual (le = lower or equal) a filtro.anoFim
			criteria.add(Restrictions.le("ano", filtro.getAnoFim()));
		}
		
		if (filtro.getTiposObra() != null && !(filtro.getTiposObra().isEmpty())) {
			// adicionamos uma restrição "in", passando uma lista de tipoObra
			criteria.add(Restrictions.in("tipoObra", filtro.getTiposObra()));
		}
		
		if (filtro.getEditora() != null && filtro.getEditora().getId() != null) {
			criteria.add(Restrictions.eq("editora.id", filtro.getEditora().getId()));
		}
		if (filtro.getAutores() != null && filtro.getAutores().size() > 0) {
			// Nota: http://stackoverflow.com/questions/17701147/hibernate-criteria-join-query-one-to-many
			criteria.createAlias("autores", "a");
			criteria.add(Restrictions.in("a.id", filtro.getAutores()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		if (filtro.getAssuntos() != null && filtro.getAssuntos().size() > 0) {
			criteria.createAlias("assuntos", "s");
			criteria.add(Restrictions.in("s.id", filtro.getAssuntos()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}
	
	public List<CD> filtrarCDs(CDFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(CD.class, "cd");
		
		if (StringUtils.isNotBlank(filtro.getComentario())) {
			criteria.add(Restrictions.ilike("comentario", filtro.getComentario(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do Título
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getSubtitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do subtítulo
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getAnoInicio() != null && filtro.getAnoInicio() > 0) {
			logger.debug(filtro.getAnoInicio());
			// id deve ser maior ou igual (ge = greater or equals) a filtro.anoInicio
			criteria.add(Restrictions.ge("ano", filtro.getAnoInicio()));
		}
		
		if (filtro.getAnoFim() != null && filtro.getAnoFim() > 0) {
			logger.debug(filtro.getAnoFim());
			// id deve ser menor ou igual (le = lower or equal) a filtro.anoFim
			criteria.add(Restrictions.le("ano", filtro.getAnoFim()));
		}
		
		if (filtro.getMidias() != null && !(filtro.getMidias().isEmpty())) {
			// adicionamos uma restrição "in", passando uma lista de midias
			criteria.add(Restrictions.in("midia", filtro.getMidias()));
		}
		
		if (filtro.getGravadora() != null && filtro.getGravadora().getId() != null) {
			criteria.add(Restrictions.eq("gravadora.id", filtro.getGravadora().getId()));
		}
		if (filtro.getCantores() != null && filtro.getCantores().size() > 0) {
			// Nota: http://stackoverflow.com/questions/17701147/hibernate-criteria-join-query-one-to-many
			criteria.createAlias("cantores", "c");
			criteria.add(Restrictions.in("c.id", filtro.getCantores()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		if (StringUtils.isNotBlank(filtro.getMusica())) {
			criteria.createAlias("musicas", "m");
			criteria.add(Restrictions.ilike("m.titulo", filtro.getMusica(), MatchMode.ANYWHERE));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		if (filtro.getAssuntos() != null && filtro.getAssuntos().size() > 0) {
			criteria.createAlias("assuntos", "s");
			criteria.add(Restrictions.in("s.id", filtro.getAssuntos()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}
	
	public List<Partitura> filtrarPartituras(PartituraFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Partitura.class, "partitura");
		
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do Título
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getSubtitulo())) {
			// pesquisa em qualquer substring que aparece em qualquer parte do subtítulo
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getAnoInicio() != null && filtro.getAnoInicio() > 0) {
			logger.debug(filtro.getAnoInicio());
			// id deve ser maior ou igual (ge = greater or equals) a filtro.anoInicio
			criteria.add(Restrictions.ge("ano", filtro.getAnoInicio()));
		}
		
		if (filtro.getAnoFim() != null && filtro.getAnoFim() > 0) {
			logger.debug(filtro.getAnoFim());
			// id deve ser menor ou igual (le = lower or equal) a filtro.anoFim
			criteria.add(Restrictions.le("ano", filtro.getAnoFim()));
		}
		
		if (filtro.getAutores() != null && filtro.getAutores().size() > 0) {
			// Nota: http://stackoverflow.com/questions/17701147/hibernate-criteria-join-query-one-to-many
			criteria.createAlias("autores", "a");
			criteria.add(Restrictions.in("a.id", filtro.getAutores()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		if (filtro.getArranjadores() != null && filtro.getArranjadores().size() > 0) {
			criteria.createAlias("arranjadores", "arranjadores");
			criteria.add(Restrictions.in("arranjadores.id", filtro.getArranjadores()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		if (filtro.getInstrumentos() != null && filtro.getInstrumentos().size() > 0) {
			criteria.createAlias("instrumentos", "i");
			criteria.add(Restrictions.in("i.id", filtro.getInstrumentos()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}
	
	public List<Fasciculo> filtrarFasciculos(FasciculoFilter filtro) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Fasciculo.class, "fasciculo");
		
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getSubtitulo())) {
			criteria.add(Restrictions.ilike("subtitulo", filtro.getSubtitulo(), MatchMode.ANYWHERE));
		}
		if (filtro.getAnoInicio() != null && filtro.getAnoInicio() > 0) {
			criteria.add(Restrictions.ge("ano", filtro.getAnoInicio()));
		}
		if (filtro.getAnoFim() != null && filtro.getAnoFim() > 0) {
			criteria.add(Restrictions.le("ano", filtro.getAnoFim()));
		}
		if (filtro.getVolume() != null && filtro.getVolume() > 0) {
			criteria.add(Restrictions.eq("volume", filtro.getVolume()));
		}
		if (filtro.getMes() != null) {
			criteria.add(Restrictions.eq("mes", filtro.getMes()));
		}
		
		if (filtro.getAssuntos() != null && filtro.getAssuntos().size() > 0) {
			// Nota: http://stackoverflow.com/questions/17701147/hibernate-criteria-join-query-one-to-many
			criteria.createAlias("assuntos", "a");
			criteria.add(Restrictions.in("a.id", filtro.getAssuntos()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		}
		
		boolean possuiFiltroEmPeriodico = StringUtils.isNotBlank(filtro.getIssn()) || filtro.getEhAssinado() != null || 
				                          filtro.getEditora() != null || filtro.getLocal() != null;
		if (possuiFiltroEmPeriodico)
			criteria.createAlias("periodico", "p");
		if (StringUtils.isNotBlank(filtro.getIssn())) {
			criteria.add(Restrictions.eq("p.issn", filtro.getIssn()));
		}
		if (filtro.getEhAssinado() != null) {
			criteria.add(Restrictions.eq("p.ehAssinado", filtro.getEhAssinado()));
		}
		if (filtro.getEditora() != null) {
			criteria.add(Restrictions.eq("p.editora", filtro.getEditora()));
		}
		if (filtro.getLocal() != null) {
			criteria.add(Restrictions.eq("p.local", filtro.getLocal()));
		}
		if (possuiFiltroEmPeriodico)
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		return criteria.addOrder(Order.asc("titulo")).list();
	}
}