package ma.cultura.emem.bean.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.calendar.Calendar;

@FacesConverter(value = "calendarConverter")
public class CalendarConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (value != null && !value.isEmpty()) {
	    java.util.Calendar cal = null;
	    try {
		Date data = convertToDate(context, (Calendar)component, value);
		cal = GregorianCalendar.getInstance();
		cal.setTime(data);
	    } catch (ParseException e) {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Data Inválida"), e);
	    }
	    return cal;
	} else {
	    return null;
	}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	String convertedValue = null;
	if (value != null) {
	    try {
		convertedValue = convertToString(context, (Calendar) component, (java.util.Calendar) value);
	    } catch (ParseException e) {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão", "Data Inválida"), e);
	    }
	    System.out.println("CALENDAR_CONVERTER...get as string...: " + value);
	}
	return convertedValue;
    }
    
    private Date convertToDate(FacesContext context, Calendar pCalendar, String value) throws ParseException{
	 DateFormat format = new SimpleDateFormat(pCalendar.getPattern(), pCalendar.calculateLocale(context));
	 format.setTimeZone(pCalendar.calculateTimeZone());
     	 return format.parse(value);
    }

    private String convertToString(FacesContext context, Calendar pCalendar, java.util.Calendar cal) throws ParseException{
	 DateFormat format = new SimpleDateFormat(pCalendar.getPattern(), pCalendar.calculateLocale(context));
	 format.setTimeZone(pCalendar.calculateTimeZone());
     	 return format.format(cal.getTime());
    }
}