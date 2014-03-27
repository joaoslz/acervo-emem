package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Autor;

public class AutorDAO extends DAO<Autor> {

	public AutorDAO() {
		super(Autor.class);
	}

	public List<Autor> likeByNome(String nome) {

		String consulta = "from Autor a where a.nome like :nome order by a.nome asc";

		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Autor> query = em.createQuery(consulta, Autor.class);
		query.setParameter("nome", "%" + nome + "%");
		List<Autor> lista = query.getResultList();

		em.close();

		return lista;
	}

	public List<Autor> listarAutoresPorIdEmOrdemDec() {

		String consulta = "select distinct a from Autor a order by a.id desc";

		EntityManager em = JPAUtil.getInstance().getEntityManager();
		TypedQuery<Autor> query = em.createQuery(consulta, Autor.class);
		List<Autor> listaAutores = query.getResultList();

		em.close();

		return listaAutores;
	}

}
