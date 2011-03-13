package org.nakeduml.eclipse;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.eclipse.core.runtime.Plugin;
public class NakedUmlEclipsePlugin extends Plugin {
	private static NakedUmlEclipsePlugin plugin;
	private ResourceBundle resourceBundle;
	public NakedUmlEclipsePlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.nakeduml.eclipse.plugin.messages");//$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}
	public static NakedUmlEclipsePlugin getDefault() {
		return plugin;
	}
	public static String getResourceString(String key) {
		ResourceBundle bundle = NakedUmlEclipsePlugin.getDefault().getResourceBundle();
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
