package ma.cultura.emem.acervo.log4j;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

public class Log4jProduces {

	@Produces
	public Logger getLogger(InjectionPoint ip){
		return Logger.getLogger(ip.getMember().getDeclaringClass());
	}
}