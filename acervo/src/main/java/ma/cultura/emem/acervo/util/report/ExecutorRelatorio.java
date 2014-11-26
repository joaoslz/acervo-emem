package ma.cultura.emem.acervo.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

@SuppressWarnings("deprecation")
public class ExecutorRelatorio implements Work {

	// Serve para configurar o tipo de arquivo para pdf,
	// dizer que é para download e obter um outputStream
	private HttpServletResponse response;

	// Parametros que serão passados para o engine do jaspereporter.
	private Map<String, Object> parametros;

	private String caminhoRelatorio;

	private String nomeArquivoSaida;

	private boolean relatorioGerado;

	public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros, String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;

		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			// pega os bytes de entrada do arquivo jasper
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);

			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);

			// se gerou pelo menos uma página
			this.relatorioGerado = print.getPages().size() > 0;

			if (this.relatorioGerado) {
				@SuppressWarnings("rawtypes")
				JRExporter exportador = new JRPdfExporter();

				// configura a saída do relatório para o browser do usuário
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

				// objeto que será usado para gerar o pdf
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);

				response.setContentType("application/pdf");
//				response.setHeader("Content-Disposition", "attachment; filename=\"" + this.nomeArquivoSaida + "\"");

				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}