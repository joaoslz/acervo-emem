package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Artigo {

    @Id
    @GeneratedValue
    private int id;

    private String titulo;
    private String assunto;

    private short paginaInicial;
    private short paginaFinal;

    @ManyToOne
    private Periodico periodico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public short getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(short paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public short getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(short paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }
}
