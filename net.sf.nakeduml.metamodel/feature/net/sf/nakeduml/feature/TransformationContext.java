package net.sf.nakeduml.feature;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;

public class TransformationContext {
	Set<Class<? extends AbstractJavaTransformationStep>> selectedFeatures = new HashSet<Class<? extends AbstractJavaTransformationStep>>();
	Set<Class<? extends AbstractJavaTransformationStep>> appliedFeatures = new HashSet<Class<? extends AbstractJavaTransformationStep>>();

	public TransformationContext(Set<Class<? extends AbstractJavaTransformationStep>> selectedFeatures) {
		super();
		this.selectedFeatures = selectedFeatures;
	}

	public TransformationContext() {
	}

	public boolean hasFeatureBeenApplied(Class<? extends TransformationStep> feature) {
		return this.appliedFeatures.contains(feature);
	}
	public boolean isFeatureSelected(Class<? extends TransformationStep> feature) {
		return this.selectedFeatures.contains(feature);
	}

	public void featureApplied(Class<? extends AbstractJavaTransformationStep> feature) {
		this.appliedFeatures.add(feature);
	}
}
