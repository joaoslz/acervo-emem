package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.controller.datamodel.RootEntityLazyDataModel;
import ma.cultura.emem.acervo.model.Emprestimo;
import ma.cultura.emem.acervo.model.Exemplar;
import ma.cultura.emem.acervo.model.Usuario;
import ma.cultura.emem.acervo.service.EmprestimoService;
import ma.cultura.emem.acervo.util.jpa.Transactional;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;

import org.apache.log4j.Logger;

@ViewScoped
@Named
public class EmprestimoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	@Inject
	private FacesMessages facesMsg;
	
	@Inject
	private RootEntityLazyDataModel<Emprestimo> lazyDataModel;
	
	@Inject
	private EmprestimoService emprestimoService;
	
	private Emprestimo emprestimo = new Emprestimo();
	
	@Transactional
	public void efetuarEmprestimo() {
		emprestimoService.efetuarEmprestimo(emprestimo);
		facesMsg.infoGlobal("Empréstimo realizado com sucesso! Item: " + emprestimo.getExemplar());
		emprestimo = new Emprestimo();
	}
	
	@Transactional
	public void efetuarDevolucao() {
		emprestimoService.efetuarDevolucao(emprestimo.getExemplar());
		facesMsg.infoGlobal("Devolução realizada com sucesso! Item: " + emprestimo.getExemplar());
		emprestimo = new Emprestimo();
		
	}
	
	@Transactional
	public void efetuarDevolucaoPorID(Integer idEmprestimo) {
		emprestimo = emprestimoService.efetuarDevolucao(idEmprestimo);
		facesMsg.infoGlobal("Devolução realizada com sucesso! Item: " + emprestimo.getExemplar());
	}
	
	public List<Exemplar> findExemplar(String id) {
		return emprestimoService.findExemplar(id);
	}
	
	public List<Usuario> findUsuario(String nome) {
		return emprestimoService.findUsuario(nome);
	}
	
	public void verificarStatusEmprestimo() {
		if (emprestimo.getExemplar() != null && !emprestimo.getExemplar().isDisponivel()) {
			Emprestimo emp = emprestimoService.findUltimoEmprestimoEmAberto(emprestimo.getExemplar());
			if (emp != null) {
				String nomeUsuario = emp.getUsuario().getNome();
				facesMsg.warnGlobal("O exemplar " + emprestimo.getExemplar() + " já está emprestado para " + nomeUsuario + "!");
			}
		}
	}
	
	public boolean isEfetuarEmprestimo() {
		return (emprestimo.getExemplar() == null || emprestimo.getExemplar().isDisponivel());
	}
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	public RootEntityLazyDataModel<Emprestimo> getLazyDataModel() {
		return lazyDataModel;
	}
}