package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.TransformationStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;

public abstract class PomGenerationStep implements TransformationStep {
	public abstract String getTargetDir();
	public abstract String getArtifactSuffix();
	public abstract Dependency[] getDependencies();
	public abstract Plugin[] getPlugins() ;
	protected EStructuralFeature addAnyElement(FeatureMap any, String elementName, String content) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(	null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();	
		node.getMixed().add(FeatureMapUtil.createTextEntry(content));
		any.add(sourceFeature, node);
		return sourceFeature;
	}

	protected AnyType addAnyElement(FeatureMap any, String elementName) {
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(	null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();	
		any.add(sourceFeature, node);
		return node;
	}

}
