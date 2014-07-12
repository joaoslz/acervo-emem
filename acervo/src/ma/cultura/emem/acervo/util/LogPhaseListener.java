package ma.cultura.emem.acervo.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.apache.log4j.Logger;

public class LogPhaseListener implements PhaseListener {

	@Inject
	private Logger logger;
	private static final long serialVersionUID = -4391626913912853862L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		logger.info("FASE: " + event.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}