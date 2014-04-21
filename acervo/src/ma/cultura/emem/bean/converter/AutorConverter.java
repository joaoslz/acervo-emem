package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.Logger;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.modelo.Autor;

@FacesConverter(value="autorConverter")
public class AutorConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(AutorConverter.class);
    private AutorDAO autorDAO = new AutorDAO();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.isEmpty()) {
            return null;
        } else {
            try {
                return autorDAO.buscaPorId(Integer.valueOf(id));
            } catch(NumberFormatException exception) {
            	LOGGER.error("Erro ao converter autor.", exception);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Autor Inválido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(((Autor) value).getId());
        }
    }
}          