package org.opaeum.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

public class OpaeumValidationPlugin extends Plugin{
	public static final String PLUGIN_ID = "org.opaeum.linkage.validation";
	private static OpaeumValidationPlugin plugin;
	public OpaeumValidationPlugin(){
		plugin=this;
	}
	public static OpaeumValidationPlugin getDefault(){
		return plugin;
	}

	public static void logWarning(String msg){
		getDefault().getLog().log(new Status(IStatus.WARNING, PLUGIN_ID, msg));
	}
	public static void logInfo(String msg){
		getDefault().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, msg));
	}
	public static IStatus logError(String message,Throwable t){
		Status result = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
		getDefault().getLog().log(result);
		return result;
	}
	public static IStatus logError(String message){
		Status result = new Status(IStatus.ERROR, PLUGIN_ID, message);
		getDefault().getLog().log(result);
		return result;
	}

}
