package net.sf.nakeduml.pomgeneration;

import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.impl.AnyTypeImpl;

public abstract class PomGenerationStep implements TransformationStep {
	protected NakedUmlConfig config;
	protected INakedModelWorkspace workspace;
	private INakedRootObject model;
	

	protected abstract OutputRoot getTargetDir();


	public String getPackaging() {
		return "jar";
	}

	public Dependency[] getDependencies() {
		return new Dependency[0];
	}

	public Dependency[] getDependencyManagementDependencies() {
		return new Dependency[0];
	}

	public Plugin[] getPluginManagements() {
		return new Plugin[0];
	}

	public Plugin[] getPlugins() {
		return new Plugin[0];
	}

	public String[] getModules() {
		return new String[0];
	}

	public static boolean containsAnyElement(FeatureMap any, String elementName) {
		for (FeatureMap.Entry entry : any) {
			if (entry.getEStructuralFeature().getName().equals(elementName)) {
				return true;
			}
		}
		return false;
	}
	
	public static void setAnyElement(FeatureMap any, String key, String property) {
		for (FeatureMap.Entry entry : any) {
			if (entry.getEStructuralFeature().getName().equals(key)) {
				AnyTypeImpl value = (AnyTypeImpl) entry.getValue();
				value.getMixed().set(0, FeatureMapUtil.createTextEntry(property));
			}
		}
	}
	
	public static EStructuralFeature addAnyElement(FeatureMap any, String elementName, String content) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		node.getMixed().add(FeatureMapUtil.createTextEntry(content));
		any.add(sourceFeature, node);
		return sourceFeature;
	}

	public static void addAnyAttribute(AnyType any, String elementName, String content) {
		EStructuralFeature attributeFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, false);
		any.eSet(attributeFeature, content);
	}

	public static AnyType addAnyElement(FeatureMap any, String elementName) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		any.add(sourceFeature, node);
		return node;
	}

	public void initialize(NakedUmlConfig config, INakedModelWorkspace workspace) {
		this.config = config;
		this.workspace=workspace;
	}
	public boolean useWorkspaceName(){
		return this.getTargetDir().useWorkspaceName();
	}
	public String getProjectName() {
		if(useWorkspaceName()){
			return this.workspace.getName()+getTargetDir().getProjectSuffix();
		}else{
			return this.model.getName()+getTargetDir().getProjectSuffix();
		}
	}



	public boolean hasFinalName() {
		return false;
	}

	public String getFinalName() {
		return this.workspace.getName();
	}

	public Properties getProperties() {
		return new Properties();
	}

	public Profile[] getProfiles() {
		return new Profile[0];
	}


	public void setModel(INakedRootObject model) {
		this.model=model;
		
	}

}
