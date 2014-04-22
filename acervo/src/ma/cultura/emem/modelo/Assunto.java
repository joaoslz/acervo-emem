package ma.cultura.emem.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = Assunto.NAMED_QUERY_LISTAR_TODOS, query = "from Assunto a order by a.id desc"),
	@NamedQuery(name = Assunto.NAMED_QUERY_PESQUISAR_POR_ASSUNTO, query = "from Assunto a where a.assunto like :assunto order by a.assunto asc") })

public class Assunto implements Serializable{
	
    private static final long serialVersionUID = -884776973799815651L;
    
    public static final String NAMED_QUERY_LISTAR_TODOS = "Assunto.listarTodos";
    public static final String NAMED_QUERY_PESQUISAR_POR_ASSUNTO = "Assunto.pesquisarPorAssunto";
    
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
