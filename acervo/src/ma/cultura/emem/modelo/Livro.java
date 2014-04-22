package ma.cultura.emem.modelo;

import java.util.Iterator;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "livro")
@NamedQueries({ 
	@NamedQuery(name = Livro.NAMED_QUERY_LISTAR_TODOS, query = "from Livro a order by a.id desc"),
	@NamedQuery(name = Livro.NAMED_QUERY_PESQUISAR_POR_ISBN, query = "from Livro l where l.isbn like :isbn")
})
public class Livro extends Obra {
	
	public static final String NAMED_QUERY_LISTAR_TODOS = "Livro.listarTodos";
	public static final String NAMED_QUERY_PESQUISAR_POR_ISBN = "Livro.pesquisarPorISBN";

	private static final long serialVersionUID = -5247199056256533770L;
	private String isbn;
	private String serie;
	private String classificacao;
	private String cutter;

	public Livro() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getCutter() {
		return cutter;
	}

	public void setCutter(String cutter) {
		this.cutter = cutter;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	public String getAutoresToString(){
		StringBuilder builder = new StringBuilder();
		if(getAutores() != null){
			Iterator<Autor> it = getAutores().iterator();
			while(it.hasNext()){
				Autor a = it.next();
				builder.append(a.getNome());
				if(it.hasNext()){
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}

	public String getAssuntosToString(){
		StringBuilder builder = new StringBuilder();
		if(getAssuntos() != null){
			Iterator<Assunto> it = getAssuntos().iterator();
			while(it.hasNext()){
				Assunto a = it.next();
				builder.append(a.getAssunto());
				if(it.hasNext()){
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}
	
	@Override
	public String toString() {
	    return super.getTitulo();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Livro){
			Livro livro = (Livro) obj;
			return this.getId().equals(livro.getId());
		}else
			return false;
	}
}