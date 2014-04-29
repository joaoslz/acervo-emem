package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Obra;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class LivroBean extends AbstractObraBean implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LivroBean.class);
	private static final long serialVersionUID = 8700247630376010790L;

	//	DAOs
	@Inject
	private LivroDAO livroDAO;
	
	// Campos para pesquisa
	private String tituloFilter;
	private String isbnFilter;

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
//		TODO implementar consulta por título no DAO.
//		else if (tituloFilter != null && tituloFilter.length() > 0)
//			livros = livroDAO.likeByTitulo(tituloFilter);
	}

	public void limparFormLivro() {
		livro = new Livro();
		LOGGER.debug("limpando form livro...");
	}
	
	public void gravar() {
		//se já possui um id é uma edição de livro(autalização), senão é um novo livro sendo cadastrado.
		boolean isEdicao = !livro.isIdNull();
		
		livro = livroDAO.atualizar(livro);

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
			livros = livroDAO.listarTodos(5);
		}
		return livros;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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

	//XXX obs.: getObra é um método abstrato do ObraBean, 
	//pois ele é necessário para operações na superclasse
	@Override
	public Obra getObra() {
		return getLivro();
	}
}