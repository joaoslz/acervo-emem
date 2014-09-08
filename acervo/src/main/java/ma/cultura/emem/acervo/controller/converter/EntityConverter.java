package ma.cultura.emem.acervo.controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

/**
 * Converter Generico.
 * Bem mais simples e prática, não precisa consultar no banco de dados, como nos anteriores.
 */
@Named
public class EntityConverter implements Converter {

	@Inject
	private Logger logger;
	
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		return component.getAttributes().get(value);
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
		if (obj == null ) 
			return "";
		try{
			//adiciona o objeto no mapa do componente para recuperá-lo posteriormente.
			//assim não precisa consultar no banco de dados.
			component.getAttributes().put(""+obj.hashCode(), obj);
			return ""+obj.hashCode();
        } catch(Exception ex) {
        	logger.error("Erro no converter", ex);
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", ex.getMessage()));
        }
	}
}