package ma.cultura.emem.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.auxiliar.Editora;

import org.apache.log4j.Logger;

public class EditoraDAO extends DAO<Editora> {

	private static final long serialVersionUID = -4059562518120940093L;
	@Inject
	private Logger logger;

	public EditoraDAO() {
		super(Editora.class);
	}


	public List<Editora> findByNome(String nome) {
		logger.debug("pesquisando editora por nome: " + nome);
		TypedQuery<Editora> query = em.createNamedQuery("Editora.findByNome", Editora.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}