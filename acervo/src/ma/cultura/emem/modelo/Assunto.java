package ma.cultura.emem.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Assunto {
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
