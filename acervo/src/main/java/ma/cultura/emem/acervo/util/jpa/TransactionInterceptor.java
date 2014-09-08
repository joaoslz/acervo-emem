package ma.cultura.emem.acervo.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		logger.debug("transação");
		EntityTransaction transaction = manager.getTransaction();
		boolean owner = false;
		
		try {
			if (!transaction.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo operações sem
				// transação)
				transaction.begin();
				transaction.rollback();
				// agora sim inicia a transação
				transaction.begin();
				owner = true;
				logger.debug("iniciando uma transação");
			}
			return context.proceed();
		} catch (Exception e) {
			if (transaction != null && owner) {
				transaction.rollback();
				logger.debug("rollback em uma transação");
			}
			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && owner) {
				transaction.commit();
				logger.debug("commit da transação");
			}
		}
	}
}