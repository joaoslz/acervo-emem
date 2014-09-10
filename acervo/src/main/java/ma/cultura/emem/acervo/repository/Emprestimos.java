package ma.cultura.emem.acervo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.cultura.emem.acervo.model.Emprestimo;
import ma.cultura.emem.acervo.model.Exemplar;

public class Emprestimos implements Serializable {
	
	private static final long serialVersionUID = 904899603185385875L;
	
	@Inject
	private EntityManager em;
	
	/**
	 * Retorna o último empréstimo em aberto para o exemplar informado.
	 * <p>
	 * Empréstimo em Aberto = Item que ainda não foi devolvido pelo usuário.
	 * </p>
	 * 
	 * @param ex
	 * @return
	 */
	public Emprestimo findUltimoEmprestimoEmAberto(Exemplar ex) {
		String jpql = "from Emprestimo e where e.dataDevolucao is null and e.exemplar.id = :idExemplar order by e.id desc";
		TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
		query.setParameter("idExemplar", ex.getId());
		List<Emprestimo> list = query.getResultList();
		if (list != null && list.size() > 0)
			return query.getResultList().get(0);
		else
			return null;
	}
	
}