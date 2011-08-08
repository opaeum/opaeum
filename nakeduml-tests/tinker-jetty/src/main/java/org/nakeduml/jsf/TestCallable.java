package org.nakeduml.jsf;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;

public class TestCallable implements Callable<String> {

	@Inject
	Logger logger;
	
	@Override
	public String call() throws Exception {
		for (int i = 0; i<10; i++) {
			logger.info("async call " + i);
			Thread.sleep(1000);
		}
		return "";		
	}

}
