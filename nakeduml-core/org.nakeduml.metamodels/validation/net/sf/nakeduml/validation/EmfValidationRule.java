package net.sf.nakeduml.validation;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum EmfValidationRule implements IValidationRule{
	BROKEN_ASSOCIATION("Broken Assocation","{0} broken irrecoverably. Please delete it");
	private String description;
	private String messagePattern;
	private EmfValidationRule(String description,String messagePattern){
		this.description = description;
		this.messagePattern = messagePattern;
	}
	public String getDescription(){
		return this.description;
	}
	public String getMessagePattern(){
		return messagePattern;
	}
}
