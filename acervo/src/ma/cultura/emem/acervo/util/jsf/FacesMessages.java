package ma.cultura.emem.acervo.util.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable {

	private static final long serialVersionUID = -6382696679850252787L;

	private void add(String id, String message, String details, Severity severity) {
		FacesMessage msg = new FacesMessage(severity, message, details);
		FacesContext.getCurrentInstance().addMessage(id, msg);
	}
	
	public void infoGlobal(String message) {
		add(null, message, "", FacesMessage.SEVERITY_INFO);
	}

	public void warnGlobal(String message) {
		add(null, message, "", FacesMessage.SEVERITY_WARN);
	}
	
	public void errorGlobal(String message) {
		add(null, message, "", FacesMessage.SEVERITY_ERROR);
	}
	
	public void errorGlobal(String message, String details) {
		add(null, message, details, FacesMessage.SEVERITY_ERROR);
	}

	public void error(String id, String message) {
		add(id, message, "",  FacesMessage.SEVERITY_ERROR);
	}
	
	public void error(String id, String message, String details) {
		add(id, message, details,  FacesMessage.SEVERITY_ERROR);
	}
	
	public void info(String id, String message) {
		add(id, message, "", FacesMessage.SEVERITY_INFO);
	}
}