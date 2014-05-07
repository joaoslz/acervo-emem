package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.PeriodicoDAO;
import ma.cultura.emem.modelo.Artigo;
import ma.cultura.emem.modelo.Obra;
import ma.cultura.emem.modelo.Periodico;

import org.apache.log4j.Logger;

@Named
@ViewScoped
public class PeriodicoBean extends AbstractObraBean implements Serializable {

	private static final long serialVersionUID = 317415519634926614L;

	private static final Logger LOGGER = Logger.getLogger(PeriodicoBean.class);

	//	DAOs
	@Inject
	private PeriodicoDAO periodicoDAO;
	// POJO para o cadastro 
	private Periodico periodico = new Periodico();
	// livros para o datatable
	private List<Periodico> periodicos = new ArrayList<Periodico>();
	
	private Artigo artigoAdd = new Artigo();

	
	public PeriodicoBean(){
		LOGGER.debug("...PeriodicoBean criado!");
	}

	public void updateListaPeriodicos(){
		periodicos = periodicoDAO.listarTodos(5);
	}

	public void limparForm() {
		periodico = new Periodico();
		LOGGER.debug("limpando form Periodico...");
	}
	
	public void gravar() {
		//se já possui um id é uma edição (autalização), senão é um novo cadastro.
		boolean isEdicao = !periodico.isIdNull();
		
		periodico = periodicoDAO.atualizar(periodico);

		if (isEdicao) {
			//Replace em caso de edição de livro.
			int index = periodicos.indexOf(periodico);
			periodicos.remove(index);
			periodicos.add(index, periodico);
			limparForm();
		}else{
			//add em caso de novo periodico.
			periodicos.add(0, periodico);
		}
	}

	public void adicionarArtigo() {
		periodico.adicionaArtigo(artigoAdd);
		artigoAdd = new Artigo();
	}


	//XXX obs.: getObra é um método abstrato do ObraBean, 
	//pois ele é necessário para operações na superclasse
	@Override
	public Obra getObra() {
		return getPeriodico();
	}

	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}

	public List<Periodico> getPeriodicos() {
		if(periodicos.isEmpty())
			updateListaPeriodicos();
		return periodicos;
	}

	/**
	 * @return the artigoAdd
	 */
	public Artigo getArtigoAdd() {
		return artigoAdd;
	}

	/**
	 * @param artigoAdd the artigoAdd to set
	 */
	public void setArtigoAdd(Artigo artigoAdd) {
		this.artigoAdd = artigoAdd;
	}
}