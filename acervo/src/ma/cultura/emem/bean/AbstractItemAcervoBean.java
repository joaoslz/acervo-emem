package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import ma.cultura.emem.bean.auxiliar.ExemplarLoteAux;
import ma.cultura.emem.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.ItemAcervo;
import ma.cultura.emem.modelo.auxiliar.Assunto;
import ma.cultura.emem.modelo.auxiliar.Editora;
import ma.cultura.emem.modelo.auxiliar.Idioma;
import ma.cultura.emem.modelo.auxiliar.Local;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

public abstract class AbstractItemAcervoBean<T extends ItemAcervo> implements Serializable{

	private static final long serialVersionUID = 766213803037842422L;

	@Inject
	protected Logger logger;
	
	@Inject
	protected DAO<T> dao;
	@Inject
	private DAO<Idioma> idiomaDAO;
	@Inject
	private DAO<Editora> editoraDAO;
	@Inject
	private DAO<Assunto> assuntoDAO;
	@Inject
	private DAO<Local> localDAO;
	@Inject
	private DAO<Exemplar> exemplarDAO;
	
	private BaseEntityLazyDataModel<T> lazyDataModel;

	// POJO para os cadastros auxiliares
	private Idioma idiomaAdd = new Idioma();
	private Editora editoraAdd = new Editora();
	private Assunto assuntoAdd = new Assunto();
	private Local localAdd = new Local();
	private ExemplarLoteAux exemplaresAdd = new ExemplarLoteAux();
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();
	
	protected T itemAcervo;

	@PostConstruct
	public void init(){
		logger.debug(this.getClass().getSimpleName() + " criado!");	
		limparForm();
	}

	//A subclasse define qual a instacia de obra
	protected abstract T getNewItemAcervo();
	public abstract String recarregarPagina();

	public void gravar() {
		//se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = getItemAcervo().getId() != null;
		if (isEdicao) {
			itemAcervo = dao.atualizar(getItemAcervo());
			limparForm();
		}else{
			itemAcervo = dao.adicionar(getItemAcervo());
			showDialogExemplares();
		}
	}
	
	public void limparForm() {
		//a subclasse define qual a instacia de obra
		itemAcervo = getNewItemAcervo();
		logger.debug("limpando form " + this.getClass().getSimpleName() + "...");
	}
	
	protected void showDialogExemplares(){
		//XXX Chamando o dialogo aqui para correcao de um bug.
		//BUG: Ao chamar o dialogo direto do <p:commandButton ha uma falha com relacao a validacao.
		//Pois mesmo que haja erro de validacao, ele continua executando a exibicao do dialogo.
		//SOLUCAO: Ao chamar o dialogo aqui no bean o problema eh corrigido, pois o JSF nao executa 
		//o metodo gravar caso haja erro de validacao.
		RequestContext.getCurrentInstance().execute("PF('dlgConfirmaExemplares').show()");
	}
	
	public void limparListaExemplares(){
		exemplares.clear();
	}
	
	public void updateListaExemplares() {
		exemplares = exemplarDAO.findByProperty("itemAcervo.id", getItemAcervo().getId());
	}

	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.atualizar(e);
	}

	public void cadastrarExemplares() {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < exemplaresAdd.getQuantidade(); i++) {
			logger.debug(i + "-" + getItemAcervo().getId());
			Exemplar exemplar = new Exemplar();
			exemplar.setItemAcervo(getItemAcervo());
			exemplar.setEhDoacao(exemplaresAdd.isEhDoacao());
			exemplar.setDataAquisicao(exemplaresAdd.getDataAquisicao());
			exemplares.add(exemplar);
		}
		exemplarDAO.adicionarLote(exemplares);
		exemplaresAdd = new ExemplarLoteAux();
		updateListaExemplares();
	}

	public void gravarIdioma() {
		idiomaDAO.adicionar(idiomaAdd);
		getItemAcervo().setIdioma(idiomaAdd);
		idiomaAdd = new Idioma();
	}
	
	public void gravarAssunto() {
		assuntoDAO.adicionar(assuntoAdd);
		getItemAcervo().addAssunto(assuntoAdd);
		assuntoAdd = new Assunto();
	}

	public void gravarLocal() {
		localDAO.adicionar(localAdd);
		getItemAcervo().setLocal(localAdd);
		localAdd = new Local();
	}

	public void gravarEditora() {
		try{
			editoraDAO.adicionar(editoraAdd);
			getItemAcervo().setEditora(editoraAdd);
			editoraAdd = new Editora();
		}catch(ConstraintViolationException exc){
			logger.error(exc.getMessage(), exc);
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome já Existente", exc.getMessage()));
		}
	}
	
	public String getStringBotaoGravar(){
		if(getItemAcervo().getId() == null)
			return "Gravar";
		else
			return "Gravar Alterações";
	}
	
	public List<Idioma> getListaIdiomas(){
		return idiomaDAO.findAll();
	}


	//..................................................................GETs e SETs
	public Editora getEditoraAdd() {
		return editoraAdd;
	}

	public void setEditoraAdd(Editora editoraAdd) {
		this.editoraAdd = editoraAdd;
	}

	public Local getLocalAdd() {
		return localAdd;
	}

	public void setLocalAdd(Local localAdd) {
		this.localAdd = localAdd;
	}

	public Assunto getAssuntoAdd() {
		return assuntoAdd;
	}

	public void setAssuntoAdd(Assunto assuntoAdd) {
		this.assuntoAdd = assuntoAdd;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public Idioma getIdiomaAdd() {
		return idiomaAdd;
	}

	public void setIdiomaAdd(Idioma idiomaAdd) {
		this.idiomaAdd = idiomaAdd;
	}

	public ExemplarLoteAux getExemplaresAdd() {
		return exemplaresAdd;
	}

	public void setExemplaresAdd(ExemplarLoteAux exemplaresAdd) {
		this.exemplaresAdd = exemplaresAdd;
	}

	public T getItemAcervo() {
		return itemAcervo;
	}

	public void setItemAcervo(T itemAcervo) {
		this.itemAcervo = itemAcervo;
	}

	public DAO<Editora> getEditoraDAO() {
		return editoraDAO;
	}

	public DAO<Assunto> getAssuntoDAO() {
		return assuntoDAO;
	}

	public DAO<Local> getLocalDAO() {
		return localDAO;
	}

	public BaseEntityLazyDataModel<T> getLazyDataModel() {
		if(lazyDataModel == null)
			lazyDataModel = new BaseEntityLazyDataModel<>(dao);
		return lazyDataModel;
	}
}