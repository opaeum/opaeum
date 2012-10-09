/*
 * 
 */
package org.opaeum.papyrus.uml.modelexplorer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.facet.infra.facet.validation.EValidatorAdapter;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.uml2.uml.UMLPackage;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements org.eclipse.ui.IStartup{
	/** The plug-in ID */
	public static final String PLUGIN_ID = "org.opaeum.papyrus.uml.modelexplorer"; //$NON-NLS-1$
	/** The plug-in shared instance */
	private static Activator plugin;
	/** The log service */
	public static LogHelper log;
	/** Default constructor */
	public Activator(){
		killExtension("org.eclipse.papyrus.infra.core.papyrusEditor", "org.eclipse.ui.editors");
	}
	private void killExtension(String papyrusId,String extensionPointId){
		org.opaeum.papyrus.propertysections.Activator.getDefault();
		// IConfigurationElement oce = findOpaeumConfigurationElement(papyrusId, extensionPointId);
		IConfigurationElement pce = findPapyrusConfigurationElement(papyrusId, extensionPointId);
		IConfigurationElement oce = findOpaeumConfigurationElement(papyrusId, extensionPointId);
		if(pce != null){
			Method method;
			try{
				method = pce.getClass().getDeclaredMethod("getConfigurationElement");
				method.setAccessible(true);
				Object oo = method.invoke(oce);
				Object po = method.invoke(pce);
				Field f = po.getClass().getDeclaredField("propertiesAndValue");
				f.setAccessible(true);
				f.set(po, new String[]{"class","org.opaeum.papyrus.editor.OpaeumMultiDiagramEditor","default","true","extensions","di","icon",
						"icons/papyrus/Papyrus_16x16.gif","id","org.eclipse.papyrus.infra.core.papyrusEditor","matchingStrategy",
						"org.opaeum.papyrus.editor.PapyrusMatchingStrategy","name","Opaeum Editor"});
				// f = po.getClass().getSuperclass().getDeclaredField("children");
				// f.setAccessible(true);
				// f.set(po, f.get(oo));
				f = po.getClass().getDeclaredField("contributorId");
				f.setAccessible(true);
				f.set(po, f.get(oo));
				System.out.println("Activator.killExtension()");
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private IConfigurationElement findOpaeumConfigurationElement(String papyrusId,String extensionPointId){
		return findConfigurationElement(papyrusId, extensionPointId, "org.opaeum");
	}
	private IConfigurationElement findPapyrusConfigurationElement(String papyrusId,String extensionPointId){
		return findConfigurationElement(papyrusId, extensionPointId, "org.eclipse.papyrus");
	}
	private IConfigurationElement findConfigurationElement(String papyrusId,String extensionPointId,String prefix){
		IExtensionRegistry r = Platform.getExtensionRegistry();
		IConfigurationElement[] configurationElementsFor = r.getConfigurationElementsFor(extensionPointId);
		for(IConfigurationElement ce:configurationElementsFor){
			String id = ce.getAttribute("id");
			if(id != null && id.startsWith(papyrusId)){
				if(ce.getContributor() != null){
					if(ce.getContributor().getName().startsWith(prefix)){
						return ce;
					}
				}
				if(ce.getAttribute("class") != null){
					if(ce.getAttribute("class").startsWith(prefix)){
						return ce;
					}
				}
			}
		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	public void start(BundleContext context) throws Exception{
		super.start(context);
		plugin = this;
		log = new LogHelper(plugin);
		// register EValidatorAdapter for selected elements
		// TODO: discouraged access
		EValidator.Registry.INSTANCE.put(UMLPackage.eINSTANCE, new EValidatorAdapter());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	public void stop(BundleContext context) throws Exception{
		plugin = null;
		super.stop(context);
	}
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault(){
		return plugin;
	}
	/**
	 * 
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 * 
	 */
	public void earlyStartup(){
	}
}
