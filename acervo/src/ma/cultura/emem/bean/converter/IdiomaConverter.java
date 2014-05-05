package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.IdiomaDAO;
import ma.cultura.emem.modelo.Idioma;

@Named
public class IdiomaConverter implements Converter {
	
	@Inject
	private IdiomaDAO idiomaDAO;
    
    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.trim().equals("")) {
            return null;
        } else {
            try {
                return idiomaDAO.buscarPorId(Integer.valueOf(id));
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Idioma Inválido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return ((Idioma) value).getId().toString();
        }
    }
}          