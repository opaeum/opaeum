package org.opaeum.feature;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opaeum.feature.StepDependency.StrategyRequirement;
import org.opaeum.feature.TransformationProcess.TransformationProgressLog;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class TransformationContext {
	boolean isIntegrationPhase;
	private boolean isGeneratingRelease=false;
	Set<Class<? extends ITransformationStep>> selectedFeatures = new HashSet<Class<? extends ITransformationStep>>();
	Set<Class<? extends ITransformationStep>> appliedFeatures = new HashSet<Class<? extends ITransformationStep>>();
	public TransformationProgressLog getLog(){
		return log;
	}

	TransformationProgressLog log;
	private Map<Class<?>,StrategyRequirement> strategies;

	public TransformationContext(Set<Class<? extends ITransformationStep>> selectedFeatures,boolean isIntegrationPhase,TransformationProgressLog log, Map<Class<?>,StrategyRequirement> strategies) {
		super();
		this.selectedFeatures = selectedFeatures;
		this.isIntegrationPhase=isIntegrationPhase;
		this.log=log;
		this.strategies=strategies;
				
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

	boolean isRelease(){
		return isGeneratingRelease;
	}

	void setRelease(boolean isGeneratingRelease){
		this.isGeneratingRelease = isGeneratingRelease;
	}

	public <T> T getStrategy(Class<T> class1){
		return (T) IntrospectionUtil.newInstance(strategies.get(class1).requires());
	}
}
