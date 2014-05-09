package ma.cultura.emem.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;

public class ObraDAO extends DAO<Obra> {
	private static final Logger LOGGER = Logger.getLogger(ObraDAO.class);
	
	public ObraDAO() {
		super(Obra.class);
	}

	@Override
	public Obra atualizar(Obra o) {
		em.getTransaction().begin();
		// FIXME recupera novamente os autores do banco para que o JPA possa
		// reconhece-los como entidades gerenciaveis.
		List<Autor> autores = new ArrayList<Autor>();
		if (o.getAutores() != null && o.getAutores().size() > 0) {
			for (Autor autor : o.getAutores()) {
				autor = em.find(Autor.class, autor.getId());
				autores.add(autor);
			}
		}
		o.setAutores(autores);
		// FIXME recupera novamente os assuntos do banco para que o JPA possa
		// reconhece-los como entidades gerenciaveis.
		List<Assunto> assuntos = new ArrayList<Assunto>();
		if (o.getAssuntos() != null && o.getAssuntos().size() > 0) {
			for (Assunto assunto : o.getAssuntos()) {
				assunto = em.find(Assunto.class, assunto.getId());
				assuntos.add(assunto);
			}
		}
		o.setAssuntos(assuntos);

		o = em.merge(o);
		em.getTransaction().commit();
		LOGGER.debug("Merge Obra: " + o.getTitulo());
		return o;
	}
	
	public List<Obra> pesquisarPorTitulo(String titulo) {
		TypedQuery<Obra> query = em.createNamedQuery(Obra.NAMED_QUERY_PESQUISAR_POR_TITULO, Obra.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList();
	}
}