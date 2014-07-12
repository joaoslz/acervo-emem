package ma.cultura.emem.acervo.bean.auxiliar;

import java.io.Serializable;
import java.util.Date;

public class ExemplarLoteAux implements Serializable{
	private static final long serialVersionUID = 1583124054830275018L;
	private int quantidade = 1;//Default 1
	private boolean ehDoacao;
	private Date dataAquisicao;

	public ExemplarLoteAux() {
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