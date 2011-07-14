package org.nakeduml.environment;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.jsf.api.listener.request.AfterFacesRequest;
import org.apache.myfaces.extensions.cdi.jsf.api.listener.request.BeforeFacesRequest;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;


public class FacesListener {

	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	Logger logger;

	public void beforeFacesRequest(@Observes @BeforeFacesRequest FacesContext ctx) {
		logger.fine("beforeFacesRequest, setting db and starting transaction");
		GraphDb.setDb(db);
		db.setTransactionMode(Mode.MANUAL);
		db.startTransaction();
	}

	public void afterFacesRequest(@Observes @AfterFacesRequest FacesContext ctx) {
		logger.fine("afterFacesRequest, stopping transaction and removing db");
		db.stopTransaction(Conclusion.SUCCESS);
		GraphDb.remove();
	}
	
}
