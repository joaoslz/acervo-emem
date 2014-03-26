package ma.cultura.emem.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.cultura.emem.dao.AssuntoDAO;
import ma.cultura.emem.modelo.Assunto;

@FacesConverter(value="assuntoConverter")
public class AssuntoConverter implements Converter {
    
    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.trim().equals("")) {
            return null;
        } else {
            try {
                System.out.println(">>>>>>>>>>>>>>>>>>>>IDASSUNTO: " + id);
                Assunto assunto = new AssuntoDAO().buscaPorId(Integer.valueOf(id));
                System.out.println(">>>>>>>>>>>>>>>>>>>>assunto: " + assunto);
                return assunto;
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Assunto Inválido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Assunto) value).getId());
        }
    }
}          