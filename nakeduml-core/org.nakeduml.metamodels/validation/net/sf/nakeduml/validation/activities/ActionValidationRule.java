package net.sf.nakeduml.validation.activities;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum ActionValidationRule implements IValidationRule{
	REQUIRED_PIN("Certain actions require input and output pins","{0} needs to have an {1} specified as its {2}"),
	SEND_SIGNAL_ACTION_REQUIRES_SIGNAL("Send Signal actions must have their Signal specified","No Signal or Notification specified for {0}"),
	CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR("Call Behavior actions must have their Behavior specified","No Method, Process or Screenflow  specified for {0}"),
	CALL_OPERATION_ACTION_REQUIRES_OPERATION("Call Operation actions must have their Behavior specified","No Operation or Reponsibility specified for {0}"),
	FEATURE_ACTION_REQUIRES_FEATURE("Structural Feature actions must have their Structural Feature specified","No Structural Feature specified for {0}"),
	VARIABLE_ACTION_REQUIRES_FEATURE("Variable actions must have their Variable specified","No Variable specified for {0}"),
	CREATE_OBJECT_ACTION_REQUIRES_CLASSIFIER("Create Object actions must have their Classifier specified","No Classifier specified for {0}"),
	EXPANSION_REGION_REQUIRES_INPUT_ELEMENT("Expansion regions must have exactly on Expansion Node as input element",
			"{0} does not have exactly one Expansion Node as input element"),
	EXPANSION_NODE_REQUIRES_EXPANSION_REGION("Expansion nodes must be specified as either input elements or output elements of an Expansion Region",
			"{0} is not associated with an Expansion Region as either input element or output element"),
	ACCEPT_CALL_RETURN_INFO_MUST_LINK("AcceptCallActions must have a returnInfo OutputPin that links up to the returnInfo InputPin of a ReplyAction",
			"{0} does not have a returnInfo output pin that links up with the returnInfo input pin of an subsequent ReplyAction"),
	REPLY_ACTION_RETURN_INFO_MUST_LINK("Reply actions must have a returnInfo InputPin that links up to the returnInfo OutputPin of a preceding AcceptCallAction",
			"{0} does not have a returnInfo InputPin that links up with the returnInfo OutputPin of an preceding AcceptCallAction"), 
	ACCEPT_CALL_REQUIRES_SINGLE_CALL_EVENT("", "{0} must have a single Trigger defined with a CallEvent specified as event ");
	private String description;
	private String messagePattern;
	private ActionValidationRule(String description,String messagePattern){
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
