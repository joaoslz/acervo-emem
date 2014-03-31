package ma.cultura.emem.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Assunto implements Serializable{
    private static final long serialVersionUID = -884776973799815651L;
    @Id
    @GeneratedValue
    private int id;
    private String assunto;

    public int getId() {
	return id;
    }

    public String getAssunto() {
	return assunto;
    }

    public void setAssunto(String assunto) {
	this.assunto = assunto;
    }

    @Override
    public String toString() {
	return assunto;
    }    
}
