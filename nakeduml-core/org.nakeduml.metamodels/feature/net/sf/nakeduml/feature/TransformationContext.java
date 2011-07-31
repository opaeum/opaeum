package net.sf.nakeduml.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransformationContext {
	boolean isIntegrationPhase;
	Set<Class<? extends TransformationStep>> selectedFeatures = new HashSet<Class<? extends TransformationStep>>();
	Set<Class<? extends TransformationStep>> appliedFeatures = new HashSet<Class<? extends TransformationStep>>();

	public TransformationContext(Set<Class<? extends TransformationStep>> selectedFeatures,boolean isIntegrationPhase) {
		super();
		this.selectedFeatures = selectedFeatures;
		this.isIntegrationPhase=isIntegrationPhase;
	}

	public TransformationContext() {
	}
	

	public boolean isIntegrationPhase(){
		return isIntegrationPhase;
	}


	public boolean hasFeatureBeenApplied(Class<? extends TransformationStep> feature) {
		return this.appliedFeatures.contains(feature);
	}

	public boolean isFeatureSelected(Class<? extends TransformationStep> feature) {
		return this.selectedFeatures.contains(feature);
	}

	public boolean isAnyOfFeaturesSelected(Class<? extends TransformationStep> ... features) {
		for (Class<? extends TransformationStep> feature : features) {
			if (this.selectedFeatures.contains(feature)) {
				return true;
			}
		}
		return false;
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
