package ma.cultura.emem.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.cultura.emem.dao.AutorDAO;
import ma.cultura.emem.modelo.Autor;

@FacesConverter(value="autorConverter")
public class AutorConverter implements Converter {
    
    private AutorDAO autorDAO = new AutorDAO();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String id) {
        if (id.trim().equals("")) {
            return null;
        } else {
            try {
                Autor autor = autorDAO.buscaPorId(Integer.valueOf(id));
                System.out.println(">>>>>>>>>>>>>>>>>>>>" + autor);
                return autor;
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Autor Inválido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Autor) value).getId());
        }
    }
}          