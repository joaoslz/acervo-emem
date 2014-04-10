package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Editora;

public class EditoraDAO extends DAO<Editora> {

    public EditoraDAO() {
	super(Editora.class);
    }
    
    public void merge(Editora editora){
	EntityManager em = JPAUtil.getInstance().getEntityManager();
	em.getTransaction().begin();

	System.out.println(editora.getId() + "..." + editora.getNome() + "..." + editora.getSite());

	em.merge(editora);
	
	System.out.println("merge: " + editora);
	
	em.getTransaction().commit();
	em.close();
    }

    public List<Editora> likeByNome(String nome){

	String consulta = "from Editora e where e.nome like :nome";
	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Editora> query = em.createQuery(consulta, Editora.class);
	query.setParameter("nome", "%" + nome + "%");
	List<Editora> lista = query.getResultList();
	em.close();

	return lista;
    }

    public List<Editora> listarTodasEditoras(){

	String consulta = "from Editora e order by e.id desc";
	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Editora> query = em.createQuery(consulta, Editora.class);
	List<Editora> lista = query.getResultList();
	em.close();

	return lista;
    }
}
