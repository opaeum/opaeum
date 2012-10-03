package org.opaeum.uim.userinteractionproperties;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class UimPluginSections extends AbstractUIPlugin{
	public static final String PLUGIN_ID = "org.opaeum.uim.userinteractionproperties";
	private static UimPluginSections plugin;
	public UimPluginSections(){
		super();
		plugin = this;
	}
	public void start(BundleContext context) throws Exception{
		super.start(context);
	}
	public void stop(BundleContext context) throws Exception{
		super.stop(context);
		plugin = null;
	}
	public static UimPluginSections getDefault(){
		return plugin;
	}
	public static String getId(){
		return getDefault().getBundle().getSymbolicName();
	}
	public static void log(String message,int level){
		IStatus status = null;
		status = new Status(level, getId(), IStatus.OK, message, null);
		log(status);
	}
	public static void log(Throwable e){
		if(e instanceof InvocationTargetException)
			e = ((InvocationTargetException) e).getTargetException();
		IStatus status = null;
		if(e instanceof CoreException)
			status = ((CoreException) e).getStatus();
		else
			status = new Status(IStatus.ERROR, getId(), IStatus.OK, "Error", e);
		log(status);
	}
	public static void log(IStatus status){
		ResourcesPlugin.getPlugin().getLog().log(status);
	}
}
