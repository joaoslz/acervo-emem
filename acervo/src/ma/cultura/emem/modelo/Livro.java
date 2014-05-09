package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "livro")
@NamedQueries({ 
	@NamedQuery(name = "Livro.listarTodos", query = "from Livro l WHERE TYPE(l) IN (Livro) order by l.id desc"),
	@NamedQuery(name = "Livro.pesquisarPorISBN", query = "from Livro l where l.isbn like :isbn and TYPE(l) IN (Livro) ")
})
public class Livro extends Obra {
	
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
	
	@Override
	public String toString() {
	    return super.getTitulo();
	}
}