package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import ma.cultura.emem.dao.ExemplarDAO;
import ma.cultura.emem.dao.LivroDAO;
import ma.cultura.emem.modelo.Exemplar;
import ma.cultura.emem.modelo.Livro;

@ManagedBean
@ViewScoped
public class ExemplarBean implements Serializable {
    
    private Livro livro = new Livro();
    
    private int quantidade;
    private boolean ehDoacao;
    private Calendar dataAquisicao;

    public String show(){
	System.out.println("showwwwwwwwwww"+livro.getId());
	return "exemplares";
    }
    
    public String cadastrarExemplares(){
	ExemplarDAO dao = new ExemplarDAO();
	for(int i = 0; i < quantidade; i++){
	    Exemplar exemplar = new Exemplar();
	    exemplar.setObra(livro);
	    exemplar.setEhDoacao(ehDoacao);
	    exemplar.setDataAquisicao(dataAquisicao);
	    dao.adicionar(exemplar);
	}
	return "exemplares";
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
	System.out.println(">>>>>>>>>>>>.......>>>>>");
        this.livro = livro;
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

    public void setEhDoacao(boolean isDoacao) {
        this.ehDoacao = isDoacao;
    }

    public Calendar getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Calendar dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
