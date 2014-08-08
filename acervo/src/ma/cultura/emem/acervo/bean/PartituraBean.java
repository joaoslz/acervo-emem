package ma.cultura.emem.acervo.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.jpa.Transactional;
import ma.cultura.emem.acervo.modelo.Partitura;
import ma.cultura.emem.acervo.modelo.auxiliar.Autor;
import ma.cultura.emem.acervo.modelo.auxiliar.Genero;
import ma.cultura.emem.acervo.modelo.auxiliar.Instrumento;

@Named
@ViewScoped
public class PartituraBean extends BaseItemAcervoBean<Partitura> implements Serializable {

	private static final long serialVersionUID = -5763773183540321467L;

	@Inject
	private DAO<Instrumento> instrumentoDAO;
	@Inject
	private DAO<Genero> generoDAO;
	@Inject
	private DAO<Autor> autorDAO;
	
	private Instrumento instrumentoAdd = new Instrumento();
	private Genero generoAdd = new Genero();
	private Autor autorAdd = new Autor();

	@Transactional
	@Override
	public void gravar() {
		// se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = getItemAcervo().getId() != null;
		itemAcervo = dao.atualizar(getPartitura());
		logger.debug("id: " + itemAcervo.getId());
		if (!isEdicao) {
			cadastrarExemplares();
		}
		limparForm();
	}

	@Transactional
	public void gravarAutor() {
		autorDAO.adicionar(autorAdd);
		logger.debug("AUTOR ADD: " + autorAdd);
		getPartitura().addAutor(autorAdd);
		logger.debug("AUTORES: " + getPartitura().getAutores());
		autorAdd = new Autor();
	}

	@Transactional
	public void gravarInstrumento() {
		instrumentoDAO.adicionar(instrumentoAdd);
		getPartitura().addInstrumento(instrumentoAdd);
		instrumentoAdd = new Instrumento();
	}

	@Transactional
	public void gravarGenero() {
		generoDAO.adicionar(generoAdd);
		getPartitura().setGenero(generoAdd);
		generoAdd = new Genero();
	}
		
	@Override
	protected void showDialogExemplares() {
		/*Sebrescrevendo este método para não exibir os exemplares na tela de partituras.
		  por isso ele está em branco, chamando apenas o limparForm.*/
		limparForm();
	}

	@Override
	protected Partitura getNewItemAcervo() {
		return new Partitura();
	}

	@Override
	public String recarregarPagina() {
		return "partitura?faces-redirect=true";
	}

	public Partitura getPartitura() {
		return getItemAcervo();
	}

	public Instrumento getInstrumentoAdd() {
		return instrumentoAdd;
	}

	public void setInstrumentoAdd(Instrumento instrumentoAdd) {
		this.instrumentoAdd = instrumentoAdd;
	}

	public Genero getGeneroAdd() {
		return generoAdd;
	}

	public void setGeneroAdd(Genero generoAdd) {
		this.generoAdd = generoAdd;
	}

	public DAO<Instrumento> getInstrumentoDAO() {
		return instrumentoDAO;
	}

	public DAO<Genero> getGeneroDAO() {
		return generoDAO;
	}

	public DAO<Autor> getAutorDAO() {
		return autorDAO;
	}

	public Autor getAutorAdd() {
		return autorAdd;
	}

	public void setAutorAdd(Autor autorAdd) {
		this.autorAdd = autorAdd;
	}
}