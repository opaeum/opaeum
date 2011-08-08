package org.nakeduml.environment;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.SystemException;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.jsf.api.listener.request.AfterFacesRequest;
import org.apache.myfaces.extensions.cdi.jsf.api.listener.request.BeforeFacesRequest;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

public class FacesListener {
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	Logger logger;
	
	public void beforeFacesRequest(@Observes @BeforeFacesRequest FacesContext ctx, @ConversationGroup(TransactionConversationGroup.class) ConversationScopedTransaction t) {
		logger.info("setting db on thread");
		try {
			System.out.println(t.getTransaction().getStatus());
		} catch (SystemException e) {
			throw new RuntimeException(e);
		}
		//Setting the threadvar on transaction creation in ConversationAwareNoSqlTransactionProxy
		db.setTransactionMode(Mode.MANUAL);
		GraphDb.setDb(db);
		if (!t.isConversationStartedInThisThread()) {
			logger.info("resume transaction");
			db.resume(t.getTransaction());
		} else {
			logger.info("transaction does not need resuming");
		}
	}

	public void afterFacesRequest(@Observes @AfterFacesRequest FacesContext ctx) {
		logger.info("removing db from thread and suspending transaction");
		db.suspend();
		GraphDb.remove();
	}

}
