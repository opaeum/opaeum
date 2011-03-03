package net.sf.nakeduml.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;

public class TransformationContext {
	Set<Class<? extends TransformationStep>> selectedFeatures = new HashSet<Class<? extends TransformationStep>>();
	Set<Class<? extends TransformationStep>> appliedFeatures = new HashSet<Class<? extends TransformationStep>>();

	public TransformationContext(Set<Class<? extends TransformationStep>> selectedFeatures) {
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

	public void featureApplied(Class<? extends TransformationStep> feature) {
		this.appliedFeatures.add(feature);
	}

	public void featuresApplied(List<? extends TransformationStep> featuresFor) {
		for (TransformationStep step : featuresFor) {
			featureApplied(step.getClass());
		}
	}
}
