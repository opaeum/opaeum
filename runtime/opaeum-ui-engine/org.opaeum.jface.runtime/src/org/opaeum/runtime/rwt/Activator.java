package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator,ServiceListener{
	public static final String PLUGIN_ID = "org.opaeum.runtime.rwt"; //$NON-NLS-1$
	public static final String ID = PLUGIN_ID;
	public static final String IMG_PROJECT = "project.gif";
	private Map<String,ServiceRegistration<?>> registration = new HashMap<String,ServiceRegistration<?>>();
	private ServletContext contextFound;
	Map<String,Image> images = new HashMap<String,Image>();
	private BundleContext bundleContext;
	private static Activator plugin;
	public Activator(){
	}
	public void start(BundleContext context) throws Exception{
		this.bundleContext = context;
		context.addServiceListener(this, "(" + Constants.OBJECTCLASS + "=" + IOpaeumApplication.class.getName() + ")");
		plugin = this;
		contextFound = null;
		ServiceReference<HttpService> sr = bundleContext.getServiceReference(HttpService.class);
		HttpService service = bundleContext.getService(sr);
		System.out.println(service);
		bundleContext.registerService(ApplicationConfiguration.class, new TestOpaeumConfiguration(), new Hashtable<String,Object>());
	}
	public void stop(BundleContext context) throws Exception{
		plugin = null;
	}
	public static Activator getDefault(){
		return plugin;
	}
	public Image getImage(String string){
		return images.get(string);
	}
	public ImageDescriptor getImageDescriptor(String string){
		try{
			final InputStream openStream = bundleContext.getBundle().getResource(string).openStream();
			return new ImageDescriptor(){
				@Override
				public ImageData getImageData(){
					return new ImageData(openStream);
				}
			};
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void serviceChanged(ServiceEvent event){
		if(event.getType() == ServiceEvent.REGISTERED){
			final IOpaeumApplication service = (IOpaeumApplication) bundleContext.getService(event.getServiceReference());
			final Dictionary<String,String> d = new Hashtable<String,String>();
			registration.put(service.getIdentifier(),
					bundleContext.registerService(ApplicationConfiguration.class.getName(), new ApplicationConfiguration(){
						@Override
						public void configure(Application application){
							Map<String,String> emptyMap = Collections.emptyMap();
							application.addEntryPoint("/" + service.getIdentifier(), service.getEntryPointType(), emptyMap);
						}
					}, d));
		}else if(event.getType() == ServiceEvent.UNREGISTERING){
			final IOpaeumApplication service = (IOpaeumApplication) bundleContext.getService(event.getServiceReference());
			registration.get(service.getIdentifier()).unregister();
			registration.remove(service.getIdentifier());
		}
	}
}
