package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.Date;

public class ExemplarPOJO implements Serializable{
	private static final long serialVersionUID = 1583124054830275018L;
	private int quantidade = 1;//Default 1
	private boolean ehDoacao;
	private Date dataAquisicao;

	public ExemplarPOJO() {
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