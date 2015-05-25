package ma.cultura.emem.acervo.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import ma.cultura.emem.acervo.util.jsf.FacesUtil;
import ma.cultura.emem.acervo.util.report.ExecutorRelatorio;

import org.apache.log4j.Logger;
import org.hibernate.Session;

@Named
@RequestScoped
public class RelatorioEtiquetaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idInicial;
	private Integer idFinal;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	// Sera usado para recuperar uma conexao JDBC para enviar ao gerador de relatório
	@Inject
	private EntityManager manager;
	
	@Inject
	private transient Logger logger;

	public void emitirEtiquetasCds() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put( "id_inicial", this.getIdInicial() );
		parametros.put( "id_final",   this.getIdFinal()   );
		
		logger.debug( "idInical : " + this.getIdInicial() );
		logger.debug( "idFinal : "  + this.getIdFinal() );

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/etiquetas_cds.jasper", 
				                                           this.response, parametros,
				                                           "RelatorioEtiquetasCDs.pdf");

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			// interrompe a execução do JSF para renderizar a página
			facesContext.responseComplete();

		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados");
		}

		session.doWork(executor);
	}

	
	public void emitirEtiquetasObras() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put( "id_inicial", this.getIdInicial() );
		parametros.put( "id_final",   this.getIdFinal()   );
		
		logger.debug( "idInical : " + this.getIdInicial() );
		logger.debug( "idFinal : "  + this.getIdFinal() );

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/etiquetas_obras.jasper", 
				                                           this.response, parametros,
				                                           "RelatorioEtiquetasObra.pdf");

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			// interrompe a execução do JSF para renderizar a página
			facesContext.responseComplete();

		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados");
		}

		session.doWork(executor);
	}

	public Integer getIdInicial() {
		return idInicial;
	}

	public void setIdInicial(Integer idIniical) {
		this.idInicial = idIniical;
	}

	public Integer getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(Integer idFinal) {
		this.idFinal = idFinal;
	}
}