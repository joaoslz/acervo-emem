package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.GeneroDAO;
import ma.cultura.emem.modelo.auxiliar.Genero;

import org.apache.log4j.Logger;

@Named
public class GeneroConverter implements Converter {

	@Inject
	private Logger logger;
	
	@Inject
    private GeneroDAO generoDAO;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.isEmpty()) {
            return null;
        } else {
            try {
                return generoDAO.buscarPorId(Integer.valueOf(id));
            } catch(NumberFormatException exception) {
            	logger.error("Erro ao converter Genero.", exception);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversao", "Genero Invalido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(((Genero) value).getId());
        }
    }
}          