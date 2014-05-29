package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Autor;

public class AutorDAO extends DAO<Autor> {

	private static final long serialVersionUID = 8780779436249062614L;

	public AutorDAO() {
		super(Autor.class);
	}

	public List<Autor> findByNome(String nome) {
		TypedQuery<Autor> query = em.createNamedQuery("Autor.findByNome", Autor.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
