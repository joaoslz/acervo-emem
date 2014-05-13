package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.InstrumentoDAO;
import ma.cultura.emem.modelo.Instrumento;

import org.apache.log4j.Logger;

@Named
public class InstrumentoConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(InstrumentoConverter.class);
	
	@Inject
    private InstrumentoDAO instrumentoDAO;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.isEmpty()) {
            return null;
        } else {
            try {
                return instrumentoDAO.buscarPorId(Integer.valueOf(id));
            } catch(NumberFormatException exception) {
            	LOGGER.error("Erro ao converter Instrumento.", exception);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversao", "Instrumento Invalido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(((Instrumento) value).getId());
        }
    }
}          