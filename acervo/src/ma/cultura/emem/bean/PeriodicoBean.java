package ma.cultura.emem.bean;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.bean.datamodel.PeriodicoLazyDataModel;
import ma.cultura.emem.dao.auxiliar.AutorDAO;
import ma.cultura.emem.modelo.Fasciculo;
import ma.cultura.emem.modelo.ItemAcervo;
import ma.cultura.emem.modelo.Periodico;
import ma.cultura.emem.modelo.auxiliar.Autor;
import ma.cultura.emem.modelo.auxiliar.MesEnum;
import ma.cultura.emem.modelo.auxiliar.PeriodicidadeEnum;

@Named
@ViewScoped
public class PeriodicoBean extends AbstractItemAcervoBean {

	private static final long serialVersionUID = -8216262637075293980L;

	@Inject
	protected AutorDAO autorDAO;
	@Inject
	private PeriodicoLazyDataModel periodicoLazyDataModel;

	private Fasciculo fasciculoAdd = new Fasciculo();

	public PeriodicoBean() {
	}

	public void adicionarFasciculo() {
		getPeriodico().addFasciculo(fasciculoAdd);
		fasciculoAdd = new Fasciculo();
	}
//
	public void removerFasciculo(int index) {
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		int index = Integer.parseInt(params.get("index").toString());
		logger.debug("REMOVENDO artigo index: " + index);
		if (!getPeriodico().getFasciculos().isEmpty()) {
			Fasciculo f = getPeriodico().getFasciculos().remove(index);
			f.setPeriodico(null);
			logger.debug("REMOVIDO DA LISTA: " + f.getTitulo());
		}
	}
	
	public void limparFormFasciculo(){
		fasciculoAdd = new Fasciculo();
	}

	public List<Autor> autocompleteAutorByNome(String nome) {
		return autorDAO.findByNome(nome);
	}
	

	@Override
	protected void showDialogExemplares() {
		//Sebrescrevendo este método para não exibir os exemplares na tela de periódicos.
		//por isso ele está em branco, chamando apenas o limparForm.
		limparForm();
	}

	@Override
	protected ItemAcervo getNewItemAcervo() {
		return new Periodico();
	}

	@Override
	public String recarregarPagina() {
		return "periodico?faces-redirect=true";
	}

	public Periodico getPeriodico() {
		return (Periodico) getItemAcervo();
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

	public Fasciculo getFasciculoAdd() {
		return fasciculoAdd;
	}

	public void setFasciculoAdd(Fasciculo fasciculoAdd) {
		this.fasciculoAdd = fasciculoAdd;
	}

}