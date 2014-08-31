package ma.cultura.emem.acervo.bean;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.dao.EmprestimoDAO;
import ma.cultura.emem.acervo.modelo.Emprestimo;
import ma.cultura.emem.acervo.modelo.Exemplar;
import ma.cultura.emem.acervo.modelo.Usuario;
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
	
	private BaseEntityLazyDataModel<Emprestimo> lazyDataModel;
	
	@Inject
	private DAO<Exemplar> exemplarDAO;
	@Inject
	private DAO<Usuario> usuarioDAO;
	@Inject
	private EmprestimoDAO emprestimoDAO;

	private Emprestimo emprestimo = new  Emprestimo();
	
	@PostConstruct
	public void init(){
		lazyDataModel = new BaseEntityLazyDataModel<Emprestimo>(emprestimoDAO.getDao());
	}
	
	@Transactional
	public void efetuarEmprestimo(){
		logger.debug(".."+emprestimo.getObservacao());
		if(emprestimo.getExemplar().isDisponivel()){
			emprestimo.setDataEmprestimo(GregorianCalendar.getInstance());
			//		emprestimo.setDataPrevista(null);//TODO estabelecer a regra para data prevista
			emprestimo.getExemplar().setDisponivel(false);
			emprestimoDAO.adicionar(emprestimo);
			exemplarDAO.atualizar(emprestimo.getExemplar());
			facesMsg.infoGlobal("Empréstimo realizado com sucesso!");
		}else{
			Emprestimo emp = emprestimoDAO.findUltimoEmprestimoEmAberto(emprestimo.getExemplar());
			emp.setDataDevolucao(GregorianCalendar.getInstance());
			emp.getExemplar().setDisponivel(true);
			emprestimoDAO.atualizar(emp);
			facesMsg.infoGlobal("Devolução realizada com sucesso!");
		}
		emprestimo = new Emprestimo();
	}	
	
	public List<Exemplar> findExemplar(String id){
		return exemplarDAO.findById(id);
	}
	
	public List<Usuario> findUsuario(String nome){
		return usuarioDAO.findByNome(nome);
	}
	
	public void verificarStatusEmprestimo(){
		if(!emprestimo.getExemplar().isDisponivel()){
			Emprestimo emp = emprestimoDAO.findUltimoEmprestimoEmAberto(emprestimo.getExemplar());
			String nomeUsuario = emp.getUsuario().getNome();
			facesMsg.warnGlobal("O exemplar <" + emprestimo.getExemplar().toString() + "> já está emprestado para " + nomeUsuario + "!");
		}
	}

	public boolean isEfetuarEmprestimo(){
		if(emprestimo.getExemplar() == null || emprestimo.getExemplar().isDisponivel())
			return true;
		else
			return false;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}


	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public BaseEntityLazyDataModel<Emprestimo> getLazyDataModel() {
		return lazyDataModel;
	}
}