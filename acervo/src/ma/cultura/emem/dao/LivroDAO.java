package ma.cultura.emem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Livro;

public class LivroDAO extends DAO<Livro> {

    public LivroDAO() {
	super(Livro.class);
    }
    
    public void adicionar(Livro l) {
	EntityManager em = JPAUtil.getInstance().getEntityManager();
	em.getTransaction().begin();
	List<Autor> autores = new ArrayList<Autor>();
	//FIXME recupera novamente os autores do banco para que o JPA possa reconhece-los como entidades gerenciaveis.
	if(l.getAutores() != null && l.getAutores().size() > 0){
	    for(Autor autor: l.getAutores()){
		autor = em.find(Autor.class, autor.getId());
		autores.add(autor);
	    }
	}
	l.setAutores(autores);
	em.persist(l);
	em.getTransaction().commit();
	em.close();
    }

    public List<Livro> listarLivros(){

	String consulta = "select distinct l from Livro l left join fetch l.autores order by l.id desc";

	EntityManager em = JPAUtil.getInstance().getEntityManager();
	TypedQuery<Livro> query = em.createQuery(consulta, Livro.class);
	List<Livro> listaObras = query.getResultList();

	em.close();

	return listaObras;
    }
}
