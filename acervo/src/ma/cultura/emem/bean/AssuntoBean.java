package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Assunto;

@ManagedBean
@RequestScoped
public class AssuntoBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private final Assunto assunto = new Assunto();

	public Assunto getAssunto() {
		return assunto;
	}

	public void gravar() {
		System.out.println("Gravando assunto " + this.assunto.getAssunto());
		new DAO<Assunto>(Assunto.class).adiciona(this.assunto);
	}
}
