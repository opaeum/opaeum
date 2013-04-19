package org.opaeum.runtime.jface.binding;

import org.eclipse.core.runtime.Status;
import org.opaeum.runtime.rwt.Activator;

public class ExtendedValidationStatus extends Status{

	public ExtendedValidationStatus(int severity,String pluginId,String message){
		super(severity, Activator.PLUGIN_ID, message);
	}
	
}
