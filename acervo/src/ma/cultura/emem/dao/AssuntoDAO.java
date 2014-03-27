package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;

public class AssuntoDAO extends DAO<Assunto> {

    public AssuntoDAO() {
	super(Assunto.class);
    }

    public List<Assunto> likeByNome(String nome){

	String consulta = "from Assunto a where a.assunto like :assunto order by a.assunto asc";

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Assunto> query = em.createQuery(consulta, Assunto.class);
	query.setParameter("assunto", "%" + nome + "%");
	List<Assunto> lista = query.getResultList();

	em.close();

	return lista;
    }
}