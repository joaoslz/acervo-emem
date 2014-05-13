package ma.cultura.emem.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.dao.ExemplarDAO;
import ma.cultura.emem.dao.IdiomaDAO;
import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.dao.ObraDAO;
import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Editora;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.Idioma;
import ma.cultura.emem.modelo.Local;
import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

public abstract class AbstractObraBean {

	@Inject
	private Logger logger;
	
	@Inject
	private IdiomaDAO idiomaDAO;
	@Inject
	protected AutorDAO autorDAO;
	@Inject
	private EditoraDAO editoraDAO;
	@Inject
	private AssuntoDAO assuntoDAO;
	@Inject
	private LocalDAO localDAO;
	@Inject
	private ExemplarDAO exemplarDAO;
	@Inject
	private ObraDAO obraDAO;

	// POJO para os cadastros auxiliares
	private Idioma idiomaAdd = new Idioma();
	private Editora editoraAdd = new Editora();
	private Local localAdd = new Local();
	private Assunto assuntoAdd = new Assunto();
	protected Autor autorAdd = new Autor();

	//TODO Criar uma classe auxiliar para encapsular esses campos.
	// Campos para cadastro de Exemplar
	private int quantidade;
	private boolean ehDoacao;
	private Date dataAquisicao;
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();
	
	protected Obra obra;

	@PostConstruct
	public void init(){
		logger.debug(this.getClass().getSimpleName() + " criado!");	
		logger.error(" error TESTE!");	
		limparForm();
	}

	//A subclasse define qual a instacia de obra
	protected abstract Obra getNewObra();
	public abstract String recarregarPagina();

	public void gravar() {
		//se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = !getObra().isIdNull();
		if (isEdicao) {
			obra = obraDAO.atualizar(getObra());
			limparForm();
		}else{
			obra = obraDAO.adicionar(getObra());
			showDialogExemplares();
		}
	}
	
	public void limparForm() {
		//a subclasse define qual a instacia de obra
		obra = getNewObra();
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
		exemplares = exemplarDAO.pesquisarExemplaresPorObra(getObra().getId());
	}

	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.atualizar(e);
	}

	public void cadastrarExemplares() {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < quantidade; i++) {
			Exemplar exemplar = new Exemplar();
			exemplar.setObra(getObra());
			exemplar.setEhDoacao(ehDoacao);
			exemplar.setDataAquisicao(dataAquisicao);
			exemplares.add(exemplar);
		}
		exemplarDAO.adicionarExemplares(exemplares);
		ehDoacao = false;
		dataAquisicao = null;
		quantidade = 0;
		updateListaExemplares();
	}

	public void gravarIdioma() {
		idiomaDAO.adicionar(idiomaAdd);
		getObra().setIdioma(idiomaAdd);
		idiomaAdd = new Idioma();
	}
	
	public void gravarAutor() {
		autorAdd.setEhAutorArtigo(false);
		autorDAO.adicionar(autorAdd);
		getObra().adicionarAutor(autorAdd);
		autorAdd = new Autor();
	}
	
	public void gravarAssunto() {
		assuntoDAO.adicionar(assuntoAdd);
		getObra().adicionarAssunto(assuntoAdd);
		assuntoAdd = new Assunto();
	}

	public void gravarLocal() {
		localDAO.adicionar(localAdd);
		getObra().setLocal(localAdd);
		localAdd = new Local();
	}

	public void gravarEditora() {
		editoraDAO.adicionar(editoraAdd);
		getObra().setEditora(editoraAdd);
		editoraAdd = new Editora();
	}
	
	public String getStringBotaoGravar(){
		if(getObra().isIdNull())
			return "Gravar";
		else
			return "Gravar Alterações";
	}
	
	public List<Idioma> getListaIdiomas(){
		return idiomaDAO.listarTodos();
	}

	public List<Editora> autocompleteEditoraByNome(String nome) {
		return editoraDAO.pesquisarPorNome(nome);
	}

	public List<Local> autocompleteLocalByNome(String nome) {
		return localDAO.pesquisarPorNome(nome);
	}

	public List<Autor> autocompleteAutorByNome(String nome) {
		return autorDAO.pesquisarPorNome(nome);
	}

	public List<Assunto> autocompleteAssuntoByAssunto(String assunto) {
		return assuntoDAO.pesquisarPorAssunto(assunto);
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

	public Autor getAutorAdd() {
		return autorAdd;
	}

	public void setAutorAdd(Autor autorAdd) {
		this.autorAdd = autorAdd;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isEhDoacao() {
		return ehDoacao;
	}

	public void setEhDoacao(boolean ehDoacao) {
		this.ehDoacao = ehDoacao;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
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

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}	
}