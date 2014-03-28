package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Assunto;

@ManagedBean
@RequestScoped
public class AssuntoBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private Assunto assunto = new Assunto();

	public Assunto getAssunto() {
		return assunto;
	}

	public void gravar() {
		System.out.println("Gravando assunto " + this.assunto.getAssunto());
		new DAO<Assunto>(Assunto.class).adicionar(this.assunto);
		this.assunto = new Assunto();
	}

	public List<Assunto> getListaAssuntoPorIdEmOrdemDec() {
		return new AssuntoDAO().listarAssuntosPorIdEmOrdemDec();
	}

}
