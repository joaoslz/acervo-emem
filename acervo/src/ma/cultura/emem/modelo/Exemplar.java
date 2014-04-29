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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = Exemplar.NAMED_QUERY_LISTAR_TODOS, query = "from Exemplar e order by e.id desc"),
	@NamedQuery(name = Exemplar.NAMED_QUERY_LISTAR_POR_OBRA, query = "from Exemplar e where e.obra.id = :idObra") })
public class Exemplar implements Serializable {

	public static final String NAMED_QUERY_LISTAR_TODOS = "Exemplar.listarTodos";
	public static final String NAMED_QUERY_LISTAR_POR_OBRA = "Exemplar.listarPorObra";

	private static final long serialVersionUID = 8689410871717711520L;
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

	public void setDataAquisicao(Date d) {
		if (d != null) {
			dataAquisicao  = GregorianCalendar.getInstance();
			dataAquisicao .setTime(d);
		}
	}
}