package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Livro;

public class LivroDAO extends DAO<Livro> {

    public LivroDAO() {
	super(Livro.class);
    }

    public List<Livro> listarLivros(){

	String consulta = "from Livro l left join fetch l.autores order by l.id desc";

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Livro> query = em.createQuery(consulta, Livro.class);
	List<Livro> listaObras = query.getResultList();

	em.close();

	return listaObras;
    }
}
