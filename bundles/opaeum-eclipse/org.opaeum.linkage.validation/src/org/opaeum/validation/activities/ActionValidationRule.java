package org.opaeum.validation.activities;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.metamodel.validation.IValidationRule;

public enum ActionValidationRule implements IValidationRule{
	REQUIRED_PIN("Certain actions require input and output pins","{0} needs to have an {1} specified as its {2}"),
	SEND_SIGNAL_ACTION_REQUIRES_SIGNAL("Send Signal actions must have their Signal specified","No Signal or Notification specified for {0}",pkg
			.getSendSignalAction_Signal()),
	CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR("Call Behavior actions must have their Behavior specified","No Method, Process or Screenflow  specified for {0}",pkg
			.getCallBehaviorAction_Behavior()),
	CALL_BEHAVIOR_ACTION_BEHAVIOR_IN_CONTEXT("Call Behavior actions must call a Behavior available locally or in the context",
			"Called Behavior {1} of {0} is not avaible locally in {2} or in the context {3}",pkg.getCallBehaviorAction_Behavior()),
	CALL_OPERATION_ACTION_TARGET_TYPE_INVALID("Call Operation's effective target type must conform to the type declaring the operation",
			"The type of {0} does not conform to the {1} which declares the invoked operation {2}",pkg.getCallOperationAction_Target()),
	CALL_OPERATION_ACTION_REQUIRES_OPERATION("Call Operation actions must have their Behavior specified","No Operation or Reponsibility specified for {0}",pkg
			.getCallOperationAction_Operation()),
	FEATURE_ACTION_REQUIRES_FEATURE("Structural Feature actions must have their Structural Feature specified","No Structural Feature specified for {0}",pkg
			.getStructuralFeatureAction_StructuralFeature()),
	VARIABLE_ACTION_REQUIRES_FEATURE("Variable actions must have their Variable specified","No Variable specified for {0}",pkg.getVariableAction_Variable()),
	CREATE_OBJECT_ACTION_REQUIRES_CLASSIFIER("Create Object actions must have their Classifier specified","No Classifier specified for {0}",pkg
			.getCreateObjectAction_Classifier()),
	EXPANSION_REGION_REQUIRES_INPUT_ELEMENT("Expansion regions must have exactly on Expansion Node as input element",
			"{0} does not have exactly one Expansion Node as input element",pkg.getExpansionRegion_InputElement()),
	EXPANSION_NODE_REQUIRES_EXPANSION_REGION("Expansion nodes must be specified as either input elements or output elements of an Expansion Region",
			"{0} is not associated with an Expansion Region as either input element or output element",pkg.getExpansionNode_RegionAsInput(),pkg
					.getExpansionNode_RegionAsOutput()),
	ACCEPT_CALL_RETURN_INFO_MUST_LINK("AcceptCallActions must have a returnInfo OutputPin that links up to the returnInfo InputPin of a ReplyAction",
			"{0} does not have a returnInfo output pin that links up with the returnInfo input pin of an subsequent ReplyAction",pkg
					.getAcceptCallAction_ReturnInformation()),
	REPLY_ACTION_RETURN_INFO_MUST_LINK("Reply actions must have a returnInfo InputPin that links up to the returnInfo OutputPin of a preceding AcceptCallAction",
			"{0} does not have a returnInfo InputPin that links up with the returnInfo OutputPin of an preceding AcceptCallAction",pkg
					.getReplyAction_ReturnInformation()),
	ACCEPT_CALL_REQUIRES_SINGLE_CALL_EVENT("","{0} must have a single Trigger defined with a CallEvent specified as event ",pkg.getAcceptEventAction_Trigger()),
	REQUIRED_MULTIPLICITY("asdf","{0} must have a multiplicity compatible with that of {1} {2}",pkg.getMultiplicityElement_UpperValue(),pkg
			.getMultiplicityElement_LowerValue(),pkg.getMultiplicityElement_IsOrdered(),pkg.getMultiplicityElement_IsUnique()),
	SEND_SIGNAL_REQUIRES_BEHAVIORED_CLASSIFIER_TARGET("Signals can only be sent to classes or interfaces",
			"Target elemet {0} of {1} evaluates to a type that is not a class or interface",pkg.getSendSignalAction_Target()),
	NUMBER_OF_PINS_AND_PARAMETERS("16","The number of {1} on {0} does not correspond with the number of {2} on the {3} specified"),
	PINS_MATCH_REQUIRED_TYPES("1126","The type of {0} does not conform to the type of {1}"),
	SEND_SIGNAL_TARGET_MUST_RECEIVE_SIGNAL("Signals cannot be sent to objects that do not declare receptions or triggers for he signal",
			"The type {2} of target element {0} of {1} does not declare a reception or trigger for signal {3}",pkg.getSendSignalAction_Target());
	private String description;
	private String messagePattern;
	private EStructuralFeature[] features;
	private ActionValidationRule(String description,String messagePattern,EStructuralFeature...features){
		this.description = description;
		this.messagePattern = messagePattern;
		this.features = features;
	}
	private ActionValidationRule(String description,String messagePattern){
		this.description = description;
		this.messagePattern = messagePattern;
		this.features = new EStructuralFeature[0];
	}
	public EStructuralFeature[] getFeatures(){
		return features;
	}
	public String getDescription(){
		return this.description;
	}
	public String getMessagePattern(){
		return messagePattern;
	}
}
