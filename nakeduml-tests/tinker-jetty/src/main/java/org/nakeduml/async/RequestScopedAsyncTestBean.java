package org.nakeduml.async;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;

@RequestScoped
@Named("requestScopedAsyncTestBean")
public class RequestScopedAsyncTestBean {

	@Inject
	Logger logger;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;

	@PostConstruct
	public void init() {
		logger.info("init");
	}
	
	@Asynchronous
	public void doAsync() {
		for (int i = 0; i < 10; i++) {
			logger.info("doAsync " + i + " " + db.getCompositeRoots().size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
