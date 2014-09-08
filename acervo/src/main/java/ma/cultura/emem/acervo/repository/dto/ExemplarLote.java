package ma.cultura.emem.acervo.repository.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.cultura.emem.acervo.model.Exemplar;
import ma.cultura.emem.acervo.model.ItemAcervo;

import org.apache.log4j.Logger;

public class ExemplarLote implements Serializable {
	private static final long serialVersionUID = 1583124054830275018L;
	private final Logger logger = Logger.getLogger(getClass());
	private int quantidade = 0;// Default 1
	private boolean ehDoacao;
	// FIXME Usar apenas o Calendar #1
	private Date dataAquisicao;
	
	public ExemplarLote() {
	}
	
	public List<Exemplar> gerarExemplares(ItemAcervo itemAcervo) {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		for (int i = 0; i < getQuantidade(); i++) {
			Exemplar exemplar = new Exemplar();
			exemplar.setItemAcervo(itemAcervo);
			exemplar.setDisponivel(true);
			exemplar.setEhDoacao(isEhDoacao());
			exemplar.setDataAquisicao(getDataAquisicao());
			exemplares.add(exemplar);
		}
		logger.debug(">>" + exemplares);
		return exemplares;
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