package ma.cultura.emem.bean.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.dao.LocalDAO;
import ma.cultura.emem.modelo.Local;

@Named
public class LocalConverter implements Converter {
    
	@Inject
    private LocalDAO localDAO;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String nome) {
        if (nome.trim().equals("")) {
            return null;
        } else {
            try {
                return localDAO.buscarPorId(nome);
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Local Inválido"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(((Local) value).getNome());
        }
    }
}          