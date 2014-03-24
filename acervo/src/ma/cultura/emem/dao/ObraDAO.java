package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Obra;

public class ObraDAO extends DAO<Obra> {

    public ObraDAO(Class<Obra> classe) {
	super(classe);
    }

    public List<Obra> buscaPorTitulo(String titulo) {
	String consulta = "select o from Obra o where o.titulo like :titulo";

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Obra> query = em.createQuery(consulta, Obra.class);
	query.setParameter("titulo", "%" + titulo + "%");
	List<Obra> listaObras = query.getResultList();

	em.close();

	return listaObras;
    }
}
