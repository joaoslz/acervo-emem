package ma.cultura.emem.dao.auxiliar;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.auxiliar.Compositor;

public class CompositorDAO extends DAO<Compositor> {

	private static final long serialVersionUID = 8780779436249062614L;

	public CompositorDAO() {
		super(Compositor.class);
	}

	public List<Compositor> findByNome(String nome) {
		TypedQuery<Compositor> query = em.createNamedQuery("Compositor.findByNome", Compositor.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
