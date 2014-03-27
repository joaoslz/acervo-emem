package ma.cultura.emem.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Local;

@FacesConverter(value="localConverter")
public class LocalConverter implements Converter {
    
    private LocalDAO localDAO = new LocalDAO();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String nome) {
        if (nome.trim().equals("")) {
            return null;
        } else {
            try {
                Local local = localDAO.buscaPorId(nome);
                return local;
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Convers�o", "Local Inv�lido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Local) value).getNome());
        }
    }
}          