package org.opaeum.uim.swt;

import java.net.URL;
import java.util.Enumeration;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UimRuntimeActivator implements BundleActivator{
	public static final String PLUGIN_ID = "org.opaeum.runtime.uim.common"; //$NON-NLS-1$
	public static final String ID = PLUGIN_ID;
	private BundleContext bundleContext;
	private ImageRegistry imageRegistry;
	private static UimRuntimeActivator plugin;
	public UimRuntimeActivator(){
	}
	public void start(BundleContext context) throws Exception{
		this.bundleContext = context;
		plugin = this;
	}
	public void stop(BundleContext context) throws Exception{
		// plugin = null;
	}
	public static UimRuntimeActivator getDefault(){
		return plugin;
	}
	public Image getImage(String string){
		if(imageRegistry == null){
			imageRegistry = new ImageRegistry(Display.getCurrent());
			scanImages("*.gif");
			scanImages("*.ico");
		}
		return imageRegistry.get(string);
	}
	private void scanImages(String extension){
		Enumeration<URL> entries = bundleContext.getBundle().findEntries("/", extension, true);
		if(entries != null){
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
					imageRegistry.put(url.getFile(), desc);
					// }
				}catch(final Exception shouldNotHappen){
					shouldNotHappen.printStackTrace();
				}
			}
		}
	}
	public ImageDescriptor getImageDescriptor(String string){
		return imageRegistry.getDescriptor(string);
	}
}
