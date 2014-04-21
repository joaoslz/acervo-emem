package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.dao.ExemplarDAO;
import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Assunto;
import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Editora;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.Idioma;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Local;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LivroBean.class);
	private static final long serialVersionUID = 8700247630376010790L;

	//	DAOs
	private LivroDAO livroDAO = new LivroDAO();
	private ExemplarDAO exemplarDAO = new ExemplarDAO();
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
			livros = livroDAO.likeByISBN(isbnFilter);
		else if (tituloFilter != null && tituloFilter.length() > 0)
			livros = livroDAO.likeByTitulo(tituloFilter);
	}

	public void limparListaExemplares(){
		exemplares.clear();
	}
	
	public void updateListaExemplares() {
		exemplares = exemplarDAO.listarExemplaresByLivroId(livro.getId());
	}

	/**
	 * Método para editar o exemplar direto da tabela.
	 * 
	 * @param event
	 */
	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.merge(e);
	}

	/**
	 * Método para limpar o formulário de cadastro de livro no momento da
	 * abertura.
	 */
	public void limparFormLivro() {
		livro = new Livro();
		//Limpa os dados do SubmmitedValue. (Ver pag. 145 apostila da caelum jsf+cdi)
		//Ao usar o immediate=true
//		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		LOGGER.debug("limparFormLivro...");
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
		exemplarDAO.cadastrarExemplares(exemplares);
		ehDoacao = false;
		dataAquisicao = null;
		quantidade = 0;
		updateListaExemplares();
	}

	public void gravarAutor() {
		AutorDAO dao = new AutorDAO();
		dao.adicionar(autorAdd);
		livro.adicionarAutor(autorAdd);
		autorAdd = new Autor();
	}

	public void gravarAssunto() {
		AssuntoDAO dao = new AssuntoDAO();
		dao.adicionar(assuntoAdd);
		livro.adicionarAssunto(assuntoAdd);
		assuntoAdd = new Assunto();
	}

	public void gravarLocal() {
		LocalDAO dao = new LocalDAO();
		dao.adicionar(localAdd);
		livro.setLocal(localAdd);
		localAdd = new Local();
	}

	public void gravarEditora() {
		EditoraDAO dao = new EditoraDAO();
		dao.adicionar(editoraAdd);
		livro.setEditora(editoraAdd);
		editoraAdd = new Editora();
	}

	public void gravar() {
		//se já possui um id é uma edição de livro(autalização), senão é um novo livro sendo cadastrado.
		boolean isEdicao = !livro.isIdNull();
		
		livro = livroDAO.merge(livro);

		if (isEdicao) {
			//Replace em caso de edição de livro.
			int index = livros.indexOf(livro);
			livros.remove(index);
			livros.add(index, livro);
			limparFormLivro();
		}else{
			//add em caso de novo livro.
			livros.add(0, livro);
			//XXX Chamando o dialogo aqui para correção de um bug.
			//BUG: Ao chamar o dialogo direto do <p:commandButton há uma falha com relação a validação.
			//Pois mesmo que haja erro de validação, ele continua executando a exibição do dialogo.
			//SOLUÇÃO: Ao chamar o dialogo aqui no bean o problema é corrigido, pois o JSF não executa 
			//o método gravar caso haja erro de validação.
			RequestContext.getCurrentInstance().execute("dlgConfirmaExemplares.show()");
		}
	}
	
	public String getStringBotaoGravar(){
		if(livro.isIdNull())
			return "Gravar";
		else
			return "Gravar Alterações";
	}

	public List<Livro> getListaLivros() {
		if (livros.isEmpty()){
			livros = livroDAO.listarLivros();
			LOGGER.debug("buscando lista de livros... size: " + livros.size());
		}
		return livros;
	}

	public List<Editora> autocompleteEditoraByNome(String nome) {
		return new EditoraDAO().likeByNome(nome);
	}

	public List<Local> autocompleteLocalByNome(String nome) {
		return new LocalDAO().likeByNome(nome);
	}

	public List<Autor> autocompleteAutorByNome(String nome) {
		return new AutorDAO().likeByNome(nome);
	}

	public List<Assunto> autocompleteAssuntoByNome(String nome) {
		return new AssuntoDAO().likeByNome(nome);
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

	public List<Idioma> getIdiomas() {
		return new DAO<Idioma>(Idioma.class).listaTodos();
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