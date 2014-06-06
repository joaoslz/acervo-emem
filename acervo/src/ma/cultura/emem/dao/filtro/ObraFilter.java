package ma.cultura.emem.dao.filtro;
import java.io.Serializable;

public class ObraFilter implements Serializable {

	private static final long serialVersionUID = -8518090812834708389L;

	private String titulo;
	private String subtitulo;
	
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String nomeCliente) {
		this.subtitulo = nomeCliente;
	}

}