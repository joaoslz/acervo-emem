package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "livro")
public class Livro extends Obra {

    private static final long serialVersionUID = -1L;

    private String isbn;
    private short serie;
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

    public short getSerie() {
	return serie;
    }

    public void setSerie(short serie) {
	this.serie = serie;
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

}