package org.opaeum.papyrus.uimadapter.actions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin{
	public static final String PLUGIN_ID = "org.opaeum.papyrus.uimadapter"; //$NON-NLS-1$
	private static Activator plugin;
	public Activator(){
		org.opaeum.papyrus.uml.modelexplorer.Activator.getDefault();
		killExtension("org.eclipse.papyrus.infra.core.papyrusEditor", "org.eclipse.ui.editors", new String[]{"class",
				"org.opaeum.papyrus.editor.OpaeumMultiDiagramEditor","default","true","extensions","di","icon","icons/papyrus/Papyrus_16x16.gif","id",
				"org.eclipse.papyrus.infra.core.papyrusEditor","matchingStrategy","org.opaeum.papyrus.editor.PapyrusMatchingStrategy","name","Opaeum Editor"});
		killExtension("org.eclipse.papyrus.views.modelexplorer.modelexplorer", "org.eclipse.ui.views", new String[]{"category",
				"org.eclipse.papyrus.views.category","class","org.opaeum.papyrus.uml.modelexplorer.OpaeumModelExplorerPageBookView","icon","icons/ModelExplorer.gif",
				"id","org.eclipse.papyrus.views.modelexplorer.modelexplorer","name","Opaeum Model Explorer","restorable","true"});
		// TODO OpaeumItemProviderAdapterFactory
		IExtensionRegistry r = Platform.getExtensionRegistry();
		for(IConfigurationElement ce:r.getConfigurationElementsFor("org.eclipse.ui.menus")){
			if(ce.getContributor() != null && ce.getContributor().getName().startsWith("org.eclipse.papyrus")){
				if(ce.getName().equals("menuContribution") && "toolbar:org.eclipse.ui.main.toolbar".equals(ce.getAttribute("locationURI"))){
//					IConfigurationElement[] children = ce.getChildren();
//					for(IConfigurationElement pce:children){
						Method method;
						try{
							method = ce.getClass().getDeclaredMethod("getConfigurationElement");
							method.setAccessible(true);
							Object po = method.invoke(ce);
							Field f = po.getClass().getSuperclass() .getDeclaredField("children");
							f.setAccessible(true);
							f.set(po, new int[]{});
							f = po.getClass().getDeclaredField("propertiesAndValue");
							f.setAccessible(true);
							f.set(po, new String[]{"locationURI",""});
						}catch(Exception e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//					}
				}
			}
		}
	}
	private void killExtension(String papyrusId,String extensionPointId,Object attributes){
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
				f.set(po, attributes);
				f = po.getClass().getDeclaredField("contributorId");
				f.setAccessible(true);
				f.set(po, f.get(oo));
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
	public void start(BundleContext context) throws Exception{
		super.start(context);
		plugin = this;
	}
	public void stop(BundleContext context) throws Exception{
		plugin = null;
		super.stop(context);
	}
	public static Activator getDefault(){
		return plugin;
	}
}
