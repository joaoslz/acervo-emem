package ma.cultura.emem.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import ma.cultura.emem.modelo.auxiliar.Autor;

@Entity
@NamedQuery(name="Artigo.findAll", query="SELECT a FROM Artigo a")
public class Artigo extends BaseEntity{

	private static final long serialVersionUID = -1467686486369558017L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100, nullable=false)
    private String titulo;
    private String assunto;

    private short paginaInicial;
    private short paginaFinal;

    @ManyToOne
    private Fasciculo fasciculo;

	@ManyToMany
	private List<Autor> autores;
	

	@Transient
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

	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addAutor(Autor autor) {
		if (autores == null)
			autores = new ArrayList<Autor>();
		autores.add(autor);
	}

	public Fasciculo getFasciculo() {
		return fasciculo;
	}

	public void setFasciculo(Fasciculo fasciculo) {
		this.fasciculo = fasciculo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}