package net.sf.nakeduml.pomgeneration;

import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;

public abstract class PomGenerationStep implements TransformationStep {
	protected NakedUmlConfig config;

	public abstract String getTargetDir();

	public abstract String getArtifactSuffix();

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

	public static EStructuralFeature addAnyElement(FeatureMap any, String elementName, String content) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		node.getMixed().add(FeatureMapUtil.createTextEntry(content));
		any.add(sourceFeature, node);
		return sourceFeature;
	}

	public static AnyType addAnyElement(FeatureMap any, String elementName) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		any.add(sourceFeature, node);
		return node;
	}

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	public String getName() {
		return this.config.getNakedUmlProjectGenName();
	}

	public String getGroupId() {
		return this.config.getNakedUmlProjectGenGroupId();
	}

	public boolean hasParent() {
		return false;
	}

	public String getParentGroupId() {
		return null;
	}

	public String getParentArtifactId() {
		return null;
	}

	public String getParentVersion() {
		return null;
	}

	public boolean hasFinalName() {
		return false;
	}

	public String getFinalName() {
		return this.config.getNakedUmlProjectGenName();
	}

	public Properties getProperties() {
		return new Properties();
	}

	public Profile[] getProfiles() {
		return new Profile[0];
	}

}
