package org.nakeduml.jetty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CxfServletListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(sce.getServletContext());
//		if (sce.getServletContext() instanceof CXFNonSpringServlet) {
//			Bus bus = ((CXFNonSpringServlet)sce.getServletContext().getServlet(name)).getBus();
//			BusFactory.setDefaultBus(bus);
//			CmApplicationWsImpl impl = new CmApplicationWsImpl();
//			Endpoint.publish("/CmApplicationWs", impl);
////		CmApplicationWs cmApplicationWs = new CmApplicationWs();
////		Endpoint.publish("/CmApplicationWs", cmApplicationWs);
//		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
