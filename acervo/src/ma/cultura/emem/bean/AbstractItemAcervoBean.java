package ma.cultura.emem.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.dao.ExemplarDAO;
import ma.cultura.emem.dao.IdiomaDAO;
import ma.cultura.emem.dao.ItemAcervoDAO;
import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.ItemAcervo;
import ma.cultura.emem.modelo.auxiliar.Assunto;
import ma.cultura.emem.modelo.auxiliar.Editora;
import ma.cultura.emem.modelo.auxiliar.Idioma;
import ma.cultura.emem.modelo.auxiliar.Local;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

public abstract class AbstractItemAcervoBean {

	@Inject
	protected Logger logger;
	
	@Inject
	private ItemAcervoDAO itemAcervoDAO;
	@Inject
	private IdiomaDAO idiomaDAO;
	@Inject
	private EditoraDAO editoraDAO;
	@Inject
	private AssuntoDAO assuntoDAO;
	@Inject
	private LocalDAO localDAO;
	@Inject
	private ExemplarDAO exemplarDAO;

	// POJO para os cadastros auxiliares
	private Idioma idiomaAdd = new Idioma();
	private Editora editoraAdd = new Editora();
	private Assunto assuntoAdd = new Assunto();
	private Local localAdd = new Local();
	private ExemplarPOJO exemplaresAdd = new ExemplarPOJO();

	private List<Exemplar> exemplares = new ArrayList<Exemplar>();
	
	protected ItemAcervo itemAcervo;

	@PostConstruct
	public void init(){
		logger.debug(this.getClass().getSimpleName() + " criado!");	
//		logger.error(" error TESTE!");	
		limparForm();
	}

	//A subclasse define qual a instacia de obra
	protected abstract ItemAcervo getNewItemAcervo();
	public abstract String recarregarPagina();

	public void gravar() {
		//se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = getItemAcervo().getId() != null;
		if (isEdicao) {
			itemAcervo = itemAcervoDAO.atualizar(getItemAcervo());
			limparForm();
		}else{
			itemAcervo = itemAcervoDAO.adicionar(getItemAcervo());
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
		RequestContext.getCurrentInstance().execute("dlgConfirmaExemplares.show()");
	}
	
	public void limparListaExemplares(){
		exemplares.clear();
	}
	
	public void updateListaExemplares() {
		exemplares = exemplarDAO.pesquisarExemplaresPorItemAcervo(getItemAcervo().getId());
	}

	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.atualizar(e);
	}

	public void cadastrarExemplares() {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < exemplaresAdd.getQuantidade(); i++) {
			Exemplar exemplar = new Exemplar();
			exemplar.setItemAcervo(getItemAcervo());
			exemplar.setEhDoacao(exemplaresAdd.isEhDoacao());
			exemplar.setDataAquisicao(exemplaresAdd.getDataAquisicao());
			exemplares.add(exemplar);
		}
		exemplarDAO.adicionarExemplares(exemplares);
		exemplaresAdd = new ExemplarPOJO();
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
		editoraDAO.adicionar(editoraAdd);
		getItemAcervo().setEditora(editoraAdd);
		editoraAdd = new Editora();
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

	public List<Editora> autocompleteEditoraByNome(String nome) {
		return editoraDAO.findByNome(nome);
	}

	public List<Local> autocompleteLocalByNome(String nome) {
		return localDAO.findByNome(nome);
	}

	public List<Assunto> autocompleteAssuntoByNome(String nome) {
		return assuntoDAO.findByNome(nome);
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

	public ExemplarPOJO getExemplaresAdd() {
		return exemplaresAdd;
	}

	public void setExemplaresAdd(ExemplarPOJO exemplaresAdd) {
		this.exemplaresAdd = exemplaresAdd;
	}

	public ItemAcervo getItemAcervo() {
		return itemAcervo;
	}

	public void setItemAcervo(ItemAcervo itemAcervo) {
		this.itemAcervo = itemAcervo;
	}
}