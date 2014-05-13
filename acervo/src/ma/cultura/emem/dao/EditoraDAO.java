package ma.cultura.emem.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ma.cultura.emem.modelo.Editora;

import org.apache.log4j.Logger;

public class EditoraDAO extends DAO<Editora> {

	private static final long serialVersionUID = -4059562518120940093L;
	private static final Logger LOGGER = Logger.getLogger(EditoraDAO.class);

	public EditoraDAO() {
		super(Editora.class);
	}


	public List<Editora> pesquisarPorNome(String nome) {
		LOGGER.debug("pesquisando editora por nome: " + nome);
		TypedQuery<Editora> query = em.createNamedQuery("Editora.pesquisarPorNome", Editora.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}