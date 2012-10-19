package org.opaeum.compiler.ocltojava;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

public class JavaCompilationPlugin extends Plugin{
	public static final String PLUGIN_ID = "org.opaeum.compilation.ocltojava";
	private static JavaCompilationPlugin plugin;
	public JavaCompilationPlugin(){
		plugin=this;
	}
	public static JavaCompilationPlugin getDefault(){
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
