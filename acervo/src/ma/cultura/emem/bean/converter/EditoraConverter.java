package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

import org.apache.log4j.Logger;

@Named
public class EditoraConverter implements Converter {
	
	private static final Logger LOGGER = Logger.getLogger(EditoraConverter.class);
    
	@Inject
    private EditoraDAO editoraDAO;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.trim().equals("")) {
            return null;
        } else {
            try {
            	LOGGER.debug("Converter editora id: " + id);
            	LOGGER.debug("Converter editora DAO: " + editoraDAO);
                return editoraDAO.buscarPorId(Integer.valueOf(id));
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Editora Inválida"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
        	LOGGER.debug("Converter editora id: " + value + " / class: " + value.getClass());
            return String.valueOf(((Editora) value).getId());
        }
    }
}          