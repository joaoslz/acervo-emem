package ma.cultura.emem.acervo.model;

import java.io.Serializable;

/**
 * TODO Implementar esta para encapsular o resultado dos calculos estatísticos
 * para cada tipo de item de acervo.
 * 
 * @author Thiago Nasper
 *
 */
public class EstatisticaItemAcervo implements Serializable {
	
	private static final long serialVersionUID = 7065570029535827890L;
	
	private String tipoItemAcervo;
	
	// Para armazenar o total de exemplares para um item de acervi.
	// Ex.: 1000 exemplares de Livros, 200 exemplares de Métodos, 100 exemplares
	// de revistas.
	private int qtdTotalExemplares;
	// Armazena a quantidade total de emprestimos realizados, para um
	// determinado item de acervo.
	// Ex.: 200 emprestimos de Livros, 50 Empréstimos de Métodos.
	private int qtdTotalEmprestimos;
	// Quantidade total exemplares emprestados no momento, para um determinado
	// item de acervo.
	private int qtdExemplaresEmprestados;
	// Quantidade total exemplares disponíveis no momento, para um determinado
	// item de acervo.
	// ex.: 980 exemplares disponíveis de Livros.
	private int qtdExemplaresDisponiveis;
	
	public String getTipoItemAcervo() {
		return tipoItemAcervo;
	}
	
	public void setTipoItemAcervo(String tipoItemAcervo) {
		this.tipoItemAcervo = tipoItemAcervo;
	}
	
	public int getQtdTotalExemplares() {
		return qtdTotalExemplares;
	}
	
	public void setQtdTotalExemplares(int qtdTotalExemplares) {
		this.qtdTotalExemplares = qtdTotalExemplares;
	}
	
	public int getQtdTotalEmprestimos() {
		return qtdTotalEmprestimos;
	}
	
	public void setQtdTotalEmprestimos(int qtdTotalEmprestimos) {
		this.qtdTotalEmprestimos = qtdTotalEmprestimos;
	}
	
	public int getQtdExemplaresEmprestados() {
		return qtdExemplaresEmprestados;
	}
	
	public void setQtdExemplaresEmprestados(int qtdExemplaresEmprestados) {
		this.qtdExemplaresEmprestados = qtdExemplaresEmprestados;
	}
	
	public int getQtdExemplaresDisponiveis() {
		return qtdExemplaresDisponiveis;
	}
	
	public void setQtdExemplaresDisponiveis(int qtdExemplaresDisponiveis) {
		this.qtdExemplaresDisponiveis = qtdExemplaresDisponiveis;
	}
}