package org.nakeduml.topcased.bpmn2;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class Bpmn2Plugin extends AbstractUIPlugin{
	private static Bpmn2Plugin plugin;
	private ResourceBundle resourceBundle;
	public Bpmn2Plugin () {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.nakeduml.eclipse.plugin.messages");//$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}
	@Override
	protected void initializeImageRegistry(ImageRegistry reg){
//		reg.put("Timer", ImageDescriptor.createFromURL(getBundle().getEntry("icons/full/objsvg/Timer.png")));
//		reg.put("Condition", ImageDescriptor.createFromURL(getBundle().getEntry("icons/full/objsvg/Condition.png")));
		reg.put("Signal", ImageDescriptor.createFromURL(getBundle().getEntry("icons/full/objsvg/Signal.svg")));
		reg.put("Condition", ImageDescriptor.createFromURL(getBundle().getEntry("icons/full/objsvg/Signal.svg")));
		reg.put("Timer", ImageDescriptor.createFromURL(getBundle().getEntry("icons/full/objsvg/Signal.svg")));
	}
	public static Bpmn2Plugin  getDefault() {
		return plugin;
	}
	public static String getResourceString(String key) {
		ResourceBundle bundle = getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}
}
