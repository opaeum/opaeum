package org.nakeduml.jetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.apache.webbeans.servlet.WebBeansConfigurationListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.util.WebservicePublisher;

public class StartJetty {
	private static Server server;
	public static void main(String[] args) throws Exception {
		// Setup the system properties to use the CXFBusFactory not the
		// SpringBusFactory
		String busFactory = System.getProperty(BusFactory.BUS_FACTORY_PROPERTY_NAME);
		System.setProperty(BusFactory.BUS_FACTORY_PROPERTY_NAME, "org.apache.cxf.bus.CXFBusFactory");
		try {

			server = new Server(new InetSocketAddress("localhost", 8080));
			ContextHandlerCollection contexts = new ContextHandlerCollection();
			server.setHandler(contexts);

			WebAppContext webapp = new WebAppContext();
			webapp.setContextPath("/tinker-jetty");
			webapp.setBaseResource(new ResourceCollection(new String[] { "./src/main/webapp", "./target" }));

			contexts.addHandler(webapp);
			server.setStopAtShutdown(true);

			CXFNonSpringServlet cxf = new CXFNonSpringServlet();
			ServletHolder servlet = new ServletHolder(cxf);
			servlet.setName("soap");
			servlet.setForcedPath("soap");
			webapp.addServlet(servlet, "/soap/*");
			webapp.addEventListener(new WebBeansConfigurationListener());
			
			server.start();

			Bus bus = cxf.getBus();
			BusFactory.setDefaultBus(bus);
			WebservicePublisher.publish();

			Thread monitor = new MonitorThread();
			monitor.start();
			//This is for junit where thread must not join
			if (args!=null && args.length>0 && !Boolean.valueOf(args[0])) {
				server.join();
			}
		} finally {
			// clean up the system properties
			if (busFactory != null) {
				System.setProperty(BusFactory.BUS_FACTORY_PROPERTY_NAME, busFactory);
			} else {
				System.clearProperty(BusFactory.BUS_FACTORY_PROPERTY_NAME);
			}
		}

	}

	private static class MonitorThread extends Thread {

		private ServerSocket socket;

		public MonitorThread() {
			setDaemon(true);
			setName("StopMonitor");
			try {
				socket = new ServerSocket(8079, 1, InetAddress.getByName("127.0.0.1"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void run() {
			System.out.println("*** running jetty 'stop' thread");
			Socket accept;
			try {
				accept = socket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
				reader.readLine();
				System.out.println("*** stopping jetty embedded server");
				server.stop();
				accept.close();
				socket.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
