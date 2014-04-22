package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.dao.ExemplarDAO;
import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Editora;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Local;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

@Named
@ConversationScoped
public class LivroBean implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LivroBean.class);
	private static final long serialVersionUID = 8700247630376010790L;

	//	DAOs
	@Inject
	private LivroDAO livroDAO;
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private EditoraDAO editoraDAO;
	@Inject
	private AssuntoDAO assuntoDAO;
	@Inject
	private LocalDAO localDAO;
	@Inject
	private ExemplarDAO exemplarDAO;
	
	// Campos para pesquisa
	private String tituloFilter;
	private String isbnFilter;

	// Campos para cadastro de Exemplar
	private int quantidade;
	private boolean ehDoacao;
	private Date dataAquisicao;
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();

	// POJO para os cadastros auxiliares
	private Editora editoraAdd = new Editora();
	private Local localAdd = new Local();
	private Assunto assuntoAdd = new Assunto();
	private Autor autorAdd = new Autor();

	// POJO para o cadastro de livro
	private Livro livro = new Livro();

	// livros para o datatable
	private List<Livro> livros = new ArrayList<Livro>();

	public LivroBean(){
		LOGGER.debug("...LivroBean criado!");
	}

	public void pesquisarLivros(){
		if (isbnFilter != null && isbnFilter.length() > 0)
			livros = livroDAO.pesquisarPorISBN(isbnFilter);
//		TODO implementar consulta por t�tulo no DAO.
//		else if (tituloFilter != null && tituloFilter.length() > 0)
//			livros = livroDAO.likeByTitulo(tituloFilter);
	}

	public void limparFormLivro() {
		livro = new Livro();
		LOGGER.debug("limparFormLivro...");
	}
	public void limparListaExemplares(){
		exemplares.clear();
	}
	
	public void updateListaExemplares() {
		exemplares = exemplarDAO.pesquisarExemplaresPorObra(livro.getId());
	}

	/**
	 * M�todo para editar o exemplar direto da tabela.
	 * 
	 * @param event
	 */
	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.atualizar(e);
	}

	public void cadastrarExemplares() {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < quantidade; i++) {
			Exemplar exemplar = new Exemplar();
			exemplar.setObra(livro);
			exemplar.setEhDoacao(ehDoacao);
			if (dataAquisicao != null) {
				Calendar c = GregorianCalendar.getInstance();
				c.setTime(dataAquisicao);
				exemplar.setDataAquisicao(c);
			}
			exemplares.add(exemplar);
		}
		exemplarDAO.adicionarExemplares(exemplares);
		ehDoacao = false;
		dataAquisicao = null;
		quantidade = 0;
		updateListaExemplares();
	}

	public void gravarAutor() {
		autorDAO.adicionar(autorAdd);
		livro.adicionarAutor(autorAdd);
		autorAdd = new Autor();
	}

	public void gravarAssunto() {
		assuntoDAO.adicionar(assuntoAdd);
		livro.adicionarAssunto(assuntoAdd);
		assuntoAdd = new Assunto();
	}

	public void gravarLocal() {
		localDAO.adicionar(localAdd);
		livro.setLocal(localAdd);
		localAdd = new Local();
	}

	public void gravarEditora() {
		editoraDAO.adicionar(editoraAdd);
		livro.setEditora(editoraAdd);
		editoraAdd = new Editora();
	}

	public void gravar() {
		//se j� possui um id � uma edi��o de livro(autaliza��o), sen�o � um novo livro sendo cadastrado.
		boolean isEdicao = !livro.isIdNull();
		
		livro = livroDAO.atualizar(livro);

		if (isEdicao) {
			//Replace em caso de edi��o de livro.
			int index = livros.indexOf(livro);
			livros.remove(index);
			livros.add(index, livro);
			limparFormLivro();
		}else{
			//add em caso de novo livro.
			livros.add(0, livro);
			//XXX Chamando o dialogo aqui para corre��o de um bug.
			//BUG: Ao chamar o dialogo direto do <p:commandButton h� uma falha com rela��o a valida��o.
			//Pois mesmo que haja erro de valida��o, ele continua executando a exibi��o do dialogo.
			//SOLU��O: Ao chamar o dialogo aqui no bean o problema � corrigido, pois o JSF n�o executa 
			//o m�todo gravar caso haja erro de valida��o.
			RequestContext.getCurrentInstance().execute("dlgConfirmaExemplares.show()");
		}
	}
	
	public String getStringBotaoGravar(){
		if(livro.isIdNull())
			return "Gravar";
		else
			return "Gravar Altera��es";
	}

	public List<Livro> getListaLivros() {
		if (livros.isEmpty()){
			livros = livroDAO.listarTodos(5);
			LOGGER.debug("buscando lista de livros... size: " + livros.size());
		}
		return livros;
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

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

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

	public String getTituloFilter() {
		return tituloFilter;
	}

	public void setTituloFilter(String tituloFilter) {
		this.tituloFilter = tituloFilter;
	}

	public String getIsbnFilter() {
		return isbnFilter;
	}

	public void setIsbnFilter(String isbnFilter) {
		this.isbnFilter = isbnFilter;
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
}