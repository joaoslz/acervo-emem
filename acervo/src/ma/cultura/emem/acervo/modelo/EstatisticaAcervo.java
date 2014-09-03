package ma.cultura.emem.acervo.modelo;

import java.io.Serializable;


public class EstatisticaAcervo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoItemAcervo;
	
	//Para armazenar o total de exemplares para um item de acervi. 
	//Ex.: 1000 exemplares de Livros, 200 exemplares de Métodos, 100 exemplares de revistas. 
	private int qtdTotalExemplares;
	//Armazena a quantidade total de emprestimos realizados, para um determinado item de acervo.
	//Ex.: 200 emprestimos de Livros, 50 Empréstimos de Métodos.
	private int qtdTotalEmprestimos;
	//Quantidade total exemplares emprestados no momento, para um determinado item de acervo.
	private int qtdExemplaresEmprestados;
	//Quantidade total exemplares disponíveis no momento, para um determinado item de acervo.
	//ex.: 980 exemplares disponíveis de Livros.
	private int qtdExemplaresDisponiveis;

}