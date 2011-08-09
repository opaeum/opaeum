package net.sf.nakeduml.feature;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransformationContext {
	boolean isIntegrationPhase;
	Set<Class<? extends ITransformationStep>> selectedFeatures = new HashSet<Class<? extends ITransformationStep>>();
	Set<Class<? extends ITransformationStep>> appliedFeatures = new HashSet<Class<? extends ITransformationStep>>();

	public TransformationContext(Set<Class<? extends ITransformationStep>> selectedFeatures,boolean isIntegrationPhase) {
		super();
		this.selectedFeatures = selectedFeatures;
		this.isIntegrationPhase=isIntegrationPhase;
	}

	public TransformationContext() {
	}
	

	public boolean isIntegrationPhase(){
		return isIntegrationPhase;
	}


	public boolean hasFeatureBeenApplied(Class<? extends ITransformationStep> feature) {
		return this.appliedFeatures.contains(feature);
	}

	public boolean isFeatureSelected(Class<? extends ITransformationStep> feature) {
		return this.selectedFeatures.contains(feature);
	}

	public boolean isAnyOfFeaturesSelected(Class<? extends ITransformationStep> ... features) {
		for (Class<? extends ITransformationStep> feature : features) {
			if (this.selectedFeatures.contains(feature)) {
				return true;
			}
		}
		return false;
	}

	public void featureApplied(Class<? extends ITransformationStep> feature) {
		this.appliedFeatures.add(feature);
	}

	public void featuresApplied(Collection<? extends ITransformationStep> featuresFor) {
		for (ITransformationStep step : featuresFor) {
			featureApplied(step.getClass());
		}
	}
}
