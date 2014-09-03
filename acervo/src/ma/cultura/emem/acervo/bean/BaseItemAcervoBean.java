package ma.cultura.emem.acervo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ma.cultura.emem.acervo.bean.auxiliar.ExemplarLoteAux;
import ma.cultura.emem.acervo.bean.datamodel.BaseEntityLazyDataModel;
import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.dao.ExemplarDAO;
import ma.cultura.emem.acervo.modelo.Exemplar;
import ma.cultura.emem.acervo.modelo.ItemAcervo;
import ma.cultura.emem.acervo.modelo.auxiliar.Assunto;
import ma.cultura.emem.acervo.modelo.auxiliar.Editora;
import ma.cultura.emem.acervo.modelo.auxiliar.Idioma;
import ma.cultura.emem.acervo.modelo.auxiliar.Local;
import ma.cultura.emem.acervo.util.jpa.Transactional;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class BaseItemAcervoBean<T extends ItemAcervo> implements Serializable {

	private static long serialVersionUID = 766213803037842422L;

	@Inject
	protected Logger logger;
	@Inject
	protected FacesMessages facesMsg;

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
	private ExemplarDAO exemplarDAO;

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
	public void init() {
		logger.debug("init: " + getClass().getSimpleName());
		lazyDataModel = new BaseEntityLazyDataModel<>(dao);
		limparForm();
	}

	// A subclasse define qual a instacia de obra
	protected abstract T getNewItemAcervo();

	public abstract String recarregarPagina();

	@Transactional
	public void gravar() {
		// se ja possui um id eh uma edicao de livro(autalizacao), senao eh um novo livro sendo cadastrado.
		boolean isEdicao = getItemAcervo().getId() != null;
		itemAcervo = dao.atualizar(getItemAcervo());
		logger.debug("id: " + itemAcervo.getId());
		if (!isEdicao) {
			cadastrarExemplares();
		}
		limparForm();
	}

	public void limparForm() {
		logger.debug("limpando form " + this.getClass().getSimpleName() + "...");
		// a subclasse define qual a instacia de obra
		itemAcervo = getNewItemAcervo();
	}

	public void limparListaExemplares() {
		logger.debug("limpando exemplares...");
		exemplares.clear();
	}

	public void updateListaExemplares() {
		exemplares = exemplarDAO.findByProperty("itemAcervo.id", getItemAcervo().getId());
	}

	@Transactional
	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		exemplarDAO.atualizar(e);
	}

	@Transactional
	public void cadastrarExemplares() {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < exemplaresAdd.getQuantidade(); i++) {
			Exemplar exemplar = new Exemplar();
			exemplar.setDisponivel(true);
			exemplar.setItemAcervo(getItemAcervo());
			exemplar.setEhDoacao(exemplaresAdd.isEhDoacao());
			exemplar.setDataAquisicao(exemplaresAdd.getDataAquisicao());
			exemplares.add(exemplar);
		}
		exemplarDAO.adicionarLote(exemplares);
		facesMsg.infoGlobal("Exemplares cadastrados com sucesso!");
		exemplaresAdd = new ExemplarLoteAux();
		updateListaExemplares();
	}

	@Transactional
	public void gravarIdioma() {
		idiomaDAO.adicionar(idiomaAdd);
		getItemAcervo().setIdioma(idiomaAdd);
		idiomaAdd = new Idioma();
	}

	@Transactional
	public void gravarAssunto() {
		assuntoDAO.adicionar(assuntoAdd);
		getItemAcervo().addAssunto(assuntoAdd);
		assuntoAdd = new Assunto();
	}

	@Transactional
	public void gravarLocal() {
		localDAO.adicionar(localAdd);
		getItemAcervo().setLocal(localAdd);
		localAdd = new Local();
	}

	@Transactional
	public void gravarEditora() {
		editoraDAO.adicionar(editoraAdd);
		getItemAcervo().setEditora(editoraAdd);
		editoraAdd = new Editora();
	}

	public String getStringBotaoGravar() {
		if (getItemAcervo().getId() == null)
			return "Gravar";
		else
			return "Gravar Alterações";
	}

	public List<Idioma> getListaIdiomas() {
		return idiomaDAO.findAll();
	}

	public String getCorBotaoExemplares(ItemAcervo it) {
		int total = exemplarDAO.countExemplares(it);
		int disponiveis = exemplarDAO.countExemplaresDisponiveis(it);
		logger.debug("TOTAL: " + total + " || Disponiveis: " + disponiveis);
		// Calcula a porcentagem de exemplares disponíveis.
		int porcentagem = (disponiveis * 100) / total;
		// transforma a porcentagem em uma escala de 1 a 10
		porcentagem = (porcentagem > 10 ? porcentagem / 10 : porcentagem);
		switch (porcentagem) {
		case 0:
			return "#FF3300";
		case 1:
			return "#FF8800";
		case 2:
			return "#FF9900";
		case 3:
			return "#FFAA00";
		case 4:
			return "#FFBB00";
		case 5:
			return "#FFCC00";
		case 6:
			return "#FFDD00";
		case 7:
			return "#FFEE00";
		case 8:
			return "#FFFF00";
		case 9:
			return "#BBFF00";
		case 10:
			return "#33FF00";
		}
		return "";
	}

	// ..................................................................GETs e SETs
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
		return lazyDataModel;
	}
}