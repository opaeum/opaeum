package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.facet.infra.facet.validation.EValidatorAdapter;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.uml2.uml.UMLPackage;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin implements org.eclipse.ui.IStartup{
	public static final String PLUGIN_ID = "org.opaeum.papyrus.uml.modelexplorer"; //$NON-NLS-1$
	private static Activator plugin;
	public static LogHelper log;
	public Activator(){
	}
	public void start(BundleContext context) throws Exception{
		super.start(context);
		plugin = this;
		log = new LogHelper(plugin);
		// register EValidatorAdapter for selected elements
		// TODO: discouraged access
		EValidator.Registry.INSTANCE.put(UMLPackage.eINSTANCE, new EValidatorAdapter());
	}
	public void stop(BundleContext context) throws Exception{
		plugin = null;
		super.stop(context);
	}
	public static Activator getDefault(){
		return plugin;
	}
	public void earlyStartup(){
	}
}
