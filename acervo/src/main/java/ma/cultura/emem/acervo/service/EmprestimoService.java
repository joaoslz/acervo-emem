package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.Emprestimo;
import ma.cultura.emem.acervo.model.Exemplar;
import ma.cultura.emem.acervo.model.Usuario;
import ma.cultura.emem.acervo.repository.CRUDRepository;
import ma.cultura.emem.acervo.repository.ConsultasRepository;
import ma.cultura.emem.acervo.repository.EmprestimoRepository;
import ma.cultura.emem.acervo.util.jpa.Transactional;

import org.apache.log4j.Logger;

public class EmprestimoService implements Serializable {
	
	private static final long serialVersionUID = 5113390920723403596L;
	
	@Inject
	private transient Logger logger;
	
	@Inject
	private CRUDRepository crudRepository;
	@Inject
	private EmprestimoRepository emprestimoRepository;
	@Inject
	private ConsultasRepository<Emprestimo> consultasEmprestimoRepository;
	@Inject
	private ConsultasRepository<Exemplar> consultasExemplarRepository;
	@Inject
	private ConsultasRepository<Usuario> consultasUsuarioRepository;
	
	@Transactional
	public void efetuarEmprestimo(Emprestimo emprestimo) {
		
		emprestimo.setDataEmprestimo(Calendar.getInstance());
		crudRepository.adicionar(emprestimo);
		
		// precisa do merge pq este exemplar veio de outra request.
		emprestimo.getExemplar().setDisponivel(false);
		crudRepository.atualizar(emprestimo.getExemplar());
		
		logger.debug("Empestimo realizado com sucesso!");
	}
	
	@Transactional
	public void efetuarDevolucao(Exemplar exemplar) {
		Emprestimo e = emprestimoRepository.findUltimoEmprestimoEmAberto(exemplar);
		if (e != null) {
			e.setDataDevolucao(Calendar.getInstance());
			// seta direto no banco, pq o EneityManager ainda está aberto.
			e.getExemplar().setDisponivel(true);
			crudRepository.atualizar(e);
		}
	}
	
	public Emprestimo efetuarDevolucao(Integer idEmprestimo) {
		Emprestimo e = consultasEmprestimoRepository.findById(idEmprestimo);
		e.setDataDevolucao(Calendar.getInstance());
		e.getExemplar().setDisponivel(true);
		return (Emprestimo) crudRepository.atualizar(e);
	}
	
	public List<Exemplar> findExemplar(String id) {
		return consultasExemplarRepository.findByIdLike(id);
	}
	
	public List<Usuario> findUsuario(String nome) {
		return consultasUsuarioRepository.findByNomeLike(nome);
	}
	
	public Emprestimo findUltimoEmprestimoEmAberto(Exemplar ex) {
		return emprestimoRepository.findUltimoEmprestimoEmAberto(ex);
	}
}