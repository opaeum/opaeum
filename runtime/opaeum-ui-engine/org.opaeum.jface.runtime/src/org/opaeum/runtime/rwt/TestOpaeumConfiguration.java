package org.opaeum.runtime.rwt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

public class TestOpaeumConfiguration implements ApplicationConfiguration{
	public void configure(Application application){
    Map<String, String> properties = new HashMap<String, String>();
    properties.put( WebClient.PAGE_TITLE, "Simple RWT Example" );
    application.addEntryPoint("/opaeum", TestOpaeumEntryPoint.class, properties);
//		application.setOperationMode(OperationMode.JEE_COMPATIBILITY);
		// application.setAttribute(ApplicationLauncher.PROPERTY_CONTEXT_NAME, "test");
	}
}