package org.opaeum.linkage;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.metamodel.validation.IValidationRule;

public enum BehaviorValidationRule implements IValidationRule{
	SINGLE_RESULT_FOR_OCL_BEHAVIOR("asdf","OCL Behavior {0} can only have one result parameter",pkg.getBehavior_OwnedParameter()),
	SPECIFICATION_CONTEXT_NOT_NULL("","{0} implements {1}, but it is not defined within a context  classifier",pkg.getBehavior_Specification()),
	SPECIFICATION_CONTEXT_CONFORMANCE("","{0} implements {1}, but its context {2} does not conform to {3}",pkg.getBehavior_Specification()),
	SPECIFICATION_PARAMETER_COUNT("","{0} which has {1} parameters cannot implement {2}, which has {3} parameters",pkg.getBehavior_OwnedParameter()),
	SPECIFICATION_PARAMETER_CONFORMANCE("","{0} which has {1} parameters cannot implement {2}, which has {3} parameters",pkg.getBehavior_OwnedParameter()),
	SPECIFICATION_PARAMETER_MULTIPLICITY("","{0} which has {1} parameters cannot implement {2}, which has {3} parameters",pkg.getBehavior_OwnedParameter());
	private EStructuralFeature[] features;
	public String getDescription(){
		return description;
	}
	public String getMessagePattern(){
		return messagePattern;
	}
	private BehaviorValidationRule(String description,String messagePattern,EStructuralFeature...features){
		this.description = description;
		this.messagePattern = messagePattern;
		this.features = features;
	}
	public EStructuralFeature[] getFeatures(){
		return features;
	}
	private String description;
	private String messagePattern;
}
