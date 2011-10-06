package org.opeum.validation.commonbehavior;

import org.opeum.metamodel.validation.IValidationRule;

public enum BehaviorValidationRule implements IValidationRule{
	SINGLE_RESULT_FOR_OCL_BEHAVIOR("asdf","Ocl Behaviors can only have one result parameter"),
	SPECIFICATION_CONTEXT_NOT_NULL("","{0} implements {1}, but it is not defined within a context  classifier"),
	SPECIFICATION_CONTEXT_CONFORMANCE("","{0} implements {1}, but its context {2} does not conform to {3}");
	public String getDescription(){
		return description;
	}
	public String getMessagePattern(){
		return messagePattern;
	}
	private BehaviorValidationRule(String description,String messagePattern){
		this.description = description;
		this.messagePattern = messagePattern;
	}
	private String description;
	private String messagePattern;
}
