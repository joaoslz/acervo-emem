package ma.cultura.emem.acervo.util.jsf;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class FacesUtil {
	
	public static <T> T getBean(String name, Class<T> classe) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getApplication().evaluateExpressionGet(ctx, "#{" + name + "}", classe);
	}
	
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}
	
	public static Object removeSessionMapValue(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}
	
	public static void setSessionMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	
	public static void hideDialog(String dialogName) {
		RequestContext.getCurrentInstance().execute("PF('" + dialogName + "').hide();");
	}
}