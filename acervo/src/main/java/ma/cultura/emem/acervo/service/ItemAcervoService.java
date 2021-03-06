package ma.cultura.emem.acervo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import ma.cultura.emem.acervo.model.Exemplar;
import ma.cultura.emem.acervo.model.ItemAcervo;
import ma.cultura.emem.acervo.model.auxiliar.Arranjador;
import ma.cultura.emem.acervo.model.auxiliar.Assunto;
import ma.cultura.emem.acervo.model.auxiliar.Autor;
import ma.cultura.emem.acervo.model.auxiliar.Cantor;
import ma.cultura.emem.acervo.model.auxiliar.Compositor;
import ma.cultura.emem.acervo.model.auxiliar.Editora;
import ma.cultura.emem.acervo.model.auxiliar.Genero;
import ma.cultura.emem.acervo.model.auxiliar.Gravadora;
import ma.cultura.emem.acervo.model.auxiliar.Idioma;
import ma.cultura.emem.acervo.model.auxiliar.Instrumento;
import ma.cultura.emem.acervo.model.auxiliar.Local;
import ma.cultura.emem.acervo.model.auxiliar.Midia;
import ma.cultura.emem.acervo.model.auxiliar.Periodico;
import ma.cultura.emem.acervo.model.auxiliar.TipoObra;
import ma.cultura.emem.acervo.repository.Insercoes;
import ma.cultura.emem.acervo.repository.Consultas;
import ma.cultura.emem.acervo.repository.dto.ExemplarLote;
import ma.cultura.emem.acervo.util.jpa.Transactional;

import org.apache.log4j.Logger;

public class ItemAcervoService implements Serializable {
	
	private static final long serialVersionUID = 5113390920723403596L;
	
	@Inject
	private transient Logger logger;
	
	@Inject
	private Insercoes crudRepository;
	@Inject
	private Consultas<Exemplar> exemplarRepository;
//	@Inject
//	private Consultas<ItemAcervo> itemAcervoRepository;
	
	// ItemAcervo
	@Inject
	private Consultas<Editora> editoraRepository;
	@Inject
	private Consultas<Assunto> assuntoRepository;
	@Inject
	private Consultas<Local> localRepository;
	
	// Obra
	@Inject
	private Consultas<Autor> autorRepository;
	@Inject
	private Consultas<Idioma> crudIdioma;
	@Inject
	private Consultas<TipoObra> crudTipoObra;
	
	// Partitura
	@Inject
	private Consultas<Genero> generoRepository;
	@Inject
	private Consultas<Arranjador> arranjadorRepository;
	@Inject
	private Consultas<Instrumento> instrumentoRepository;
	
	// CD
	@Inject
	private Consultas<Gravadora> gravadoraRepository;
	@Inject
	private Consultas<Cantor> cantorRepository;
	@Inject
	private Consultas<Compositor> compositorRepository;
	@Inject
	private Consultas<Midia> midiaRepository;
	
	// Fasciculo
	@Inject
	private Consultas<Periodico> periodicoRepository;
	
	@Transactional
	public void cadastrarItemAcervo(ItemAcervo itemAcervo, ExemplarLote lote) {
		boolean isNovoCadastro = itemAcervo.getId() == null;
		itemAcervo = (ItemAcervo) crudRepository.atualizar(itemAcervo);
		if (isNovoCadastro) {
			logger.debug("cadastrando exemplares");
			crudRepository.adicionarLote(lote.gerarExemplares(itemAcervo));
		}
		logger.debug("cadastro de item acervo");
	}

	@Transactional
	public void remover(Exemplar exemplar){
		crudRepository.remover(exemplar);
	}
	
	@Transactional
	public void atualizarExemplar(Exemplar exemplar) {
		crudRepository.atualizar(exemplar);
	}
	
	@Transactional
	public void cadastrarExemplarLote(List<Exemplar> exemplares) {
		crudRepository.adicionarLote(exemplares);
	}
	
	public List<Exemplar> findExemplares(Integer idItemAcervo) {
		return exemplarRepository.findByProperty("itemAcervo.id", idItemAcervo);
	}
	
	// -------Métodos para os autocompletes e combos
	
	public List<Editora> findEditoras(String nome) {
		return editoraRepository.findByNomeLike(nome);
	}
	
	public List<Assunto> findAssuntos(String nome) {
		return assuntoRepository.findByNomeLike(nome);
	}
	
	public List<Local> findLocais(String nome) {
		return localRepository.findByNomeLike(nome);
	}
	
	// OBRA
	public List<TipoObra> findAllTiposObra() {
		return crudTipoObra.findAll();
	}
	
	public List<Idioma> findAllIdiomas() {
		return crudIdioma.findAll();
	}
	
	public List<Autor> findAutores(String nome) {
		return autorRepository.findByNomeLike(nome);
	}
	
	// PARTITURA
	public List<Genero> findGeneros(String nome) {
		return generoRepository.findByNomeLike(nome);
	}
	
	public List<Arranjador> findArranjadores(String nome) {
		return arranjadorRepository.findByNomeLike(nome);
	}
	
	public List<Instrumento> findInstrumentos(String nome) {
		return instrumentoRepository.findByNomeLike(nome);
	}
	
	// CD
	public List<Gravadora> findGravadoras(String nome) {
		return gravadoraRepository.findByNomeLike(nome);
	}
	
	public List<Cantor> findCantores(String nome) {
		return cantorRepository.findByNomeLike(nome);
	}
	
	public List<Compositor> findCompositores(String nome) {
		return compositorRepository.findByNomeLike(nome);
	}
	
	public List<Midia> findAllMidias() {
		return midiaRepository.findAll();
	}
	
	// FASCICULO
	public List<Periodico> findAllPeriodicos() {
		return periodicoRepository.findAll();
	}
}