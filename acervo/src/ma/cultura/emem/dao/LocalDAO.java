package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Local;

public class LocalDAO extends DAO<Local> {

    public LocalDAO() {
	super(Local.class);
    }

    public List<Local> likeByNome(String nome){

	String consulta = "from Local l where l.nome like :nome";

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Local> query = em.createQuery(consulta, Local.class);
	query.setParameter("nome", "%" + nome + "%");
	List<Local> lista = query.getResultList();

	em.close();

	return lista;
    }
}
