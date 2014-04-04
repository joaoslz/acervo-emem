package ma.cultura.emem.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Exemplar implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private boolean ehDoacao;
    @Temporal(TemporalType.DATE)
    private Calendar dataAquisicao;

    @Column(columnDefinition = "text")
    private String observacao;

    @ManyToOne
    private Obra obra;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public boolean isEhDoacao() {
	return ehDoacao;
    }

    public void setEhDoacao(boolean ehDoacao) {
	this.ehDoacao = ehDoacao;
    }

    public Obra getObra() {
	return obra;
    }

    public void setObra(Obra obra) {
	this.obra = obra;
    }

    public String getObservacao() {
	return observacao;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    public Calendar getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Calendar dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}