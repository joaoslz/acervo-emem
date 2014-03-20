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

}
