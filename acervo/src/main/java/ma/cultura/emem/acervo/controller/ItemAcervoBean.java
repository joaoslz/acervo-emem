package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ma.cultura.emem.acervo.controller.datamodel.RootEntityLazyDataModel;
import ma.cultura.emem.acervo.model.Exemplar;
import ma.cultura.emem.acervo.model.ItemAcervo;
import ma.cultura.emem.acervo.model.auxiliar.Arranjador;
import ma.cultura.emem.acervo.model.auxiliar.Assunto;
import ma.cultura.emem.acervo.model.auxiliar.Autor;
import ma.cultura.emem.acervo.model.auxiliar.Editora;
import ma.cultura.emem.acervo.model.auxiliar.Genero;
import ma.cultura.emem.acervo.model.auxiliar.Idioma;
import ma.cultura.emem.acervo.model.auxiliar.Instrumento;
import ma.cultura.emem.acervo.model.auxiliar.Local;
import ma.cultura.emem.acervo.model.auxiliar.TipoObra;
import ma.cultura.emem.acervo.repository.dto.ExemplarLote;
import ma.cultura.emem.acervo.service.ExemplarService;
import ma.cultura.emem.acervo.service.ItemAcervoService;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public abstract class ItemAcervoBean<T extends ItemAcervo> implements Serializable {
	
	private static final long serialVersionUID = 490085903681173331L;
	
	@Inject
	protected Logger logger;
	@Inject
	protected FacesMessages facesMsg;
	
	@Inject
	protected ItemAcervoService itemAcervoService;
	@Inject
	private ExemplarService exemplarService;
	
	@Inject
	private RootEntityLazyDataModel<T> lazyDataModel;
	
	private ExemplarLote exemplaresLote = new ExemplarLote();
	
	protected T entity;
	
	// A subclasse define qual a instacia de obra
	protected abstract T getNewItemAcervo();
	
	public abstract String recarregarPagina();
	
	@PostConstruct
	public void init() {
		logger.debug("init: " + getClass().getSimpleName());
		limparForm();
	}
	
	public void gravar() {
		itemAcervoService.cadastrarItemAcervo(entity, exemplaresLote);
		facesMsg.infoGlobal("Informações gravadas com sucesso para o Item:  " + entity);
		limparForm();
	}
	
	public void limparForm() {
		entity = getNewItemAcervo();
		exemplaresLote = new ExemplarLote();
	}
	
	public void editExempar(RowEditEvent event) {
		Exemplar e = (Exemplar) event.getObject();
		itemAcervoService.atualizarExemplar(e);
		facesMsg.infoGlobal("Exemplar alterado com sucesso!");
	}
	
	public void cadastrarExemplares() {
		itemAcervoService.cadastrarExemplarLote(exemplaresLote.gerarExemplares(entity));
		facesMsg.infoGlobal("Exemplares cadastrados com sucesso!");
		exemplaresLote = new ExemplarLote();
	}
	
	public List<Exemplar> getListaExemplares() {
		return itemAcervoService.findExemplares(getEntity().getId());
	}
	
	public String getStringBotaoGravar() {
		return entity.getId() == null ? "Gravar" : "Gravar Alterações";
	}
	
	public int getQtdExemplares(ItemAcervo it) {
		return exemplarService.countExemplares(it);
	}
	
	public int getQtdExemplaresDisponiveis(ItemAcervo it) {
		return exemplarService.countExemplaresDisponiveis(it);
	}
	
	public String getCorBotaoExemplares(ItemAcervo it) {
		int total = getQtdExemplares(it);
		int disponiveis = getQtdExemplaresDisponiveis(it);
		logger.debug("TOTAL: " + total + " || Disponiveis: " + disponiveis);
		if (total > 0) {
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
		}
		return "red";
	}
	
	// Cadastros via Dialog
	public void setAssunto(Assunto a) {
		getEntity().addAssunto(a);
		logger.debug("Adicionando Assunto na Obra: " + a);
	}
	
	public List<Idioma> getListaIdiomas() {
		return itemAcervoService.findAllIdiomas();
	}
	
	public List<TipoObra> getListaTiposObra() {
		return itemAcervoService.findAllTiposObra();
	}
	
	public List<Autor> findAutores(String nome) {
		return itemAcervoService.findAutores(nome);
	}
	
	public List<Editora> findEditoras(String nome) {
		return itemAcervoService.findEditoras(nome);
	}
	
	public List<Assunto> findAssuntos(String nome) {
		return itemAcervoService.findAssuntos(nome);
	}
	
	public List<Local> findLocais(String nome) {
		return itemAcervoService.findLocais(nome);
	}
	
	public List<Genero> findGeneros(String nome) {
		return itemAcervoService.findGeneros(nome);
	}
	
	public List<Instrumento> findInstrumentos(String nome) {
		return itemAcervoService.findInstrumentos(nome);
	}
	
	public List<Arranjador> findArranjadores(String nome) {
		return itemAcervoService.findArranjadores(nome);
	}
	
	public RootEntityLazyDataModel<T> getLazyDataModel() {
		return lazyDataModel;
	}
	
	// ..................................................................GETs e
	
	public T getEntity() {
		return entity;
	}
	
	public void setEntity(T itemAcervo) {
		this.entity = itemAcervo;
	}
	
	public ExemplarLote getExemplaresLote() {
		return exemplaresLote;
	}
	
	public void setExemplaresLote(ExemplarLote exemplaresLote) {
		this.exemplaresLote = exemplaresLote;
	}
}