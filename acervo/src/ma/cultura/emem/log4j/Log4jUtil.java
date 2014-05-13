package ma.cultura.emem.log4j;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

public class Log4jUtil {

	@Produces
	public Logger getLogger(InjectionPoint ip){
		return Logger.getLogger(ip.getMember().getDeclaringClass());
	}
}
