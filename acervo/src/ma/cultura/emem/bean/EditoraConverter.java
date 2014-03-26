package ma.cultura.emem.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.cultura.emem.dao.EditoraDAO;
import ma.cultura.emem.modelo.Editora;

@FacesConverter(value="editoraConverter")
public class EditoraConverter implements Converter {
    
    private EditoraDAO editoraDAO = new EditoraDAO();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
        	System.out.println(".........."+submittedValue);
                int id = Integer.parseInt(submittedValue);
                Editora editora = editoraDAO.buscaPorId(id);
                return editora;

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Editora Inválida"));
            }
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Editora) value).getId());
        }
    }
}          