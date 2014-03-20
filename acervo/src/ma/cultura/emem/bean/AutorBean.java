package ma.cultura.emem.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import ma.cultura.emem.dao.DAO;
import ma.cultura.emem.modelo.Autor;

import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 3905906075866017417L;

	private final Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		new DAO<Autor>(Autor.class).adiciona(this.autor);
	}

	public void gravar(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;

		// if(username != null &&&& username.equals("admin") && password != null
		// && password.equals("admin")) {
		// loggedIn = true;
		// msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
		// username);
		// } else {
		// loggedIn = false;
		// msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
		// "Invalid credentials");
		// }
		//
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// context.addCallbackParam("loggedIn", loggedIn);
	}
}
