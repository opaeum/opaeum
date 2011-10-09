package org.opaeum.linkage;

import org.opaeum.metamodel.validation.IValidationRule;

public enum ActivityValidationRule implements IValidationRule{
	AT_LEAST_ONE_DEFAULT_FLOW("1","Action {0} requires at least one flow without a guard condition"),
	NO_CONDITIONAL_FLOW_TO_JOIN("3","The conditional flow {0} is not allowed into {1} join {2}"),
	ONE_FLOW_TO_OBJECT_NODES("4","Only one object flow is allowed into ObjectNode {0}"),
	
	ONE_FLOW_FROM_OBJECT_NODES("5","Only one object flow is allowed from ObjectNode {0}"),
	LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS("Long running actions can only be used from business processes","{0} is used from {1} which is a method, but should be used from a business process only"),
	ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS("AcceptEventActions can only be used in activities marked as a processes","AcceptEventAction {0} can only be used in activities marked as a processes"),
	PIN_FOR_PARAMETER("15","No pin found for parameter"),
	MORE_PINS_THAN_PARAMETERS("16","The number of {1} on {0} does not correspond with the number of {2} on the {3} specified"),
	OBJECT_NODE_TYPES_MUST_MATCH("17","The type of the {1}, the source of {0}, does not correspond with the type of its target {2}"),
	SINGLE_OUTGOING_EDGE("17","{0} may only have a single outgoing edge"),
	EITHER_OBJECT_FLOW_OR_CONTROL_FLOW("17","{0} may only have a single outgoing edge"),
	;
	ActivityValidationRule(String description,String messagePattern){
		this.description = description;
		this.messagePattern = messagePattern;
	}
	public String getDescription(){
		return description;
	}
	public String getMessagePattern(){
		if(messagePattern==null){
			return name();
		}
		return messagePattern;
	}
	private String description;
	private String messagePattern;
}
