package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fasciculo implements Serializable{

	private static final long serialVersionUID = -1467686486369558017L;

	@Id
    @GeneratedValue
    private Integer id;

    private Short volume;
    private Short numero;
	private Short ano;
    private String periodo;

    private short paginaInicial;
    private short paginaFinal;
    
    @ManyToOne
    private String mesInicio;
    private String mesFim;
    
	@OneToMany(mappedBy = "fasciculo")
	private List<Exemplar> exemplares;

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
