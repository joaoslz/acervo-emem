package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.PeriodicoLazyDataModel;
import ma.cultura.emem.dao.PeriodicoDAO;
import ma.cultura.emem.modelo.Periodico;
import ma.cultura.emem.modelo.auxiliar.MesEnum;
import ma.cultura.emem.modelo.auxiliar.PeriodicidadeEnum;

import org.apache.log4j.Logger;

@Named
@ViewScoped
public class PeriodicoBean implements Serializable {

	private static final long serialVersionUID = -8216262637075293980L;

	@Inject
	private Logger logger;
	
	@Inject
	private PeriodicoDAO periodicoDAO;
	
	@Inject
	private PeriodicoLazyDataModel periodicoLazyDataModel;
	
	private Periodico periodico = new Periodico();
	

	@PostConstruct
	public void init() {
		logger.debug("PeriodicoBean criado");
	}

	public void gravar() {
		//se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
//		boolean isEdicao = periodico.getId() != null;
//		if (isEdicao) {
		periodico = periodicoDAO.atualizar(periodico);
		limparForm();
//		}else{
//			periodico = periodicoDAO.adicionar(periodico);
//		}
	}
	
	public void limparForm(){
		periodico = new Periodico();
	}
	
	public String recarregarPagina() {
		return "periodico?faces-redirect=true";
	}
	
	public String getStringBotaoGravar(){
		if(periodico.getId() == null)
			return "Gravar";
		else
			return "Gravar Alterações";
	}
	
	public PeriodicidadeEnum[] getListaPeriodicidade(){
		return PeriodicidadeEnum.values();
	}
	
	public MesEnum[] getListaMeses(){
		return MesEnum.values();
	}

	public PeriodicoLazyDataModel getPeriodicoLazyDataModel() {
		return periodicoLazyDataModel;
	}

	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}
}