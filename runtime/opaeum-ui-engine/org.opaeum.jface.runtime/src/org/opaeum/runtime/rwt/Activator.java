package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
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
	private BundleContext bundleContext;
	private ImageRegistry imageRegistry;
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
	}
	public void stop(BundleContext context) throws Exception{
//		plugin = null;
	}
	public static Activator getDefault(){
		return plugin;
	}
	public Image getImage(String string){
		if(imageRegistry == null){
			imageRegistry = new ImageRegistry(Display.getCurrent());
			Enumeration<URL> entries = bundleContext.getBundle().findEntries("/", "*.gif", true);
			while(entries.hasMoreElements()){
				URL url = (URL) entries.nextElement();
				// chop off the '/' and the extension
				String id = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
				try{
					// IPath path = new Path(url.getFile().substring(1));
					// URL url = FileLocator.find(bundleContext.getBundle(), path, null);
					// if(url != null){
					ImageDescriptor desc = ImageDescriptor.createFromURL(url);
					imageRegistry.put(id, desc);
					// }
				}catch(final Exception shouldNotHappen){
					shouldNotHappen.printStackTrace();
				}
			}
		}
		return imageRegistry.get(string);
	}
	public ImageDescriptor getImageDescriptor(String string){
		return imageRegistry.getDescriptor(string);
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
			ServiceRegistration<?> sr = registration.get(service.getIdentifier());
			sr.unregister();
			service.getEnvironment().unregister();
			registration.remove(service.getIdentifier());
		}
	}
}
