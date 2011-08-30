package org.nakeduml.eclipse;

import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import net.sf.nakeduml.feature.ISourceFolderStrategy;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class NakedUmlEclipsePlugin extends Plugin implements IRegistryChangeListener{
	public static final String TRANSFORMATION_STEP_EXTENSION_POINT_ID = "transformationStep";
	public static final String SOURCE_FOLDER_DEFINITION_STRATEGY_EXTENSION_POINT_ID = "sourceFolderStrategy";
	private static NakedUmlEclipsePlugin plugin;
	private ResourceBundle resourceBundle;
	private Set<Class<? extends ITransformationStep>> transformationSteps = new HashSet<Class<? extends ITransformationStep>>();
	private Set<ISourceFolderStrategy> sourceFolderStrategies = new HashSet<ISourceFolderStrategy>();
	public NakedUmlEclipsePlugin(){
		super();
		plugin = this;
		try{
			resourceBundle = ResourceBundle.getBundle("org.nakeduml.eclipse.plugin.messages");//$NON-NLS-1$
		}catch(MissingResourceException x){
			resourceBundle = null;
		}
		addTransformationSteps(Platform.getExtensionRegistry().getConfigurationElementsFor("org.nakeduml.eclipse", TRANSFORMATION_STEP_EXTENSION_POINT_ID));
		addSourceFolderStrategies(Platform.getExtensionRegistry().getConfigurationElementsFor("org.nakeduml.eclipse",
				SOURCE_FOLDER_DEFINITION_STRATEGY_EXTENSION_POINT_ID));
		Platform.getExtensionRegistry().addRegistryChangeListener(this);
	}
	public static NakedUmlEclipsePlugin getDefault(){
		return plugin;
	}
	public static String getResourceString(String key){
		ResourceBundle bundle = NakedUmlEclipsePlugin.getDefault().getResourceBundle();
		try{
			return (bundle != null) ? bundle.getString(key) : key;
		}catch(MissingResourceException e){
			return key;
		}
	}
	public ResourceBundle getResourceBundle(){
		return resourceBundle;
	}
	public static String getPluginId(){
		return getDefault().getBundle().getSymbolicName();
	}
	@Override
	public void registryChanged(IRegistryChangeEvent event){
		IExtensionDelta[] extensionDeltas = event.getExtensionDeltas("org.nakeduml.eclipse", TRANSFORMATION_STEP_EXTENSION_POINT_ID);
		for(IExtensionDelta delta:extensionDeltas){
			if(delta.getKind() == IExtensionDelta.ADDED){
				addTransformationSteps(delta.getExtension().getConfigurationElements());
			}
		}
		extensionDeltas = event.getExtensionDeltas("org.nakeduml.eclipse", SOURCE_FOLDER_DEFINITION_STRATEGY_EXTENSION_POINT_ID);
		for(IExtensionDelta delta:extensionDeltas){
			if(delta.getKind() == IExtensionDelta.ADDED){
				addSourceFolderStrategies(delta.getExtension().getConfigurationElements());
			}
		}
	}
	public void addSourceFolderStrategies(IConfigurationElement[] configurationElements){
		try{
			for(IConfigurationElement ce:configurationElements){
				ISourceFolderStrategy s = (ISourceFolderStrategy) ce.createExecutableExtension("className");
				sourceFolderStrategies.add(s);
				NakedUmlConfig.registerClass(s.getClass());
			}
		}catch(CoreException e){
			e.printStackTrace();
		}
	}
	public Set<ISourceFolderStrategy> getSourceFolderStrategies(){
		return sourceFolderStrategies;
	}
	public void addTransformationSteps(IConfigurationElement[] configurationElements){
		try{
			for(IConfigurationElement ce:configurationElements){
				Class<? extends ITransformationStep> class1 = (Class<? extends ITransformationStep>) ce.createExecutableExtension("className").getClass();
				transformationSteps.add(class1);
				NakedUmlConfig.registerClass(class1);
			}
		}catch(CoreException e){
			e.printStackTrace();
		}
	}
	public Set<Class<? extends ITransformationStep>> getTransformationSteps(){
		return transformationSteps;
	}
}
