package net.sf.nakeduml.validation.activities;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum ActivityValidationRule implements IValidationRule{
	AT_LEAST_ONE_DEFAULT_FLOW("1","Action{0} requires at least one flow without a guard condition"),
	IMPLICIT_FORK_OR_DECISION("2",null),
	NO_CONDITIONAL_FLOW_TO_JOIN("3",null),
	ONE_FLOW_TO_OBJECT_NODES("4",null),
	ONE_FLOW_FROM_OBJECT_NODES("5",null),
	UNIQUE_GUARD_NAMES("6",null),
	UNIQUE_EMULATED_ATTRIBUTES("7",null),
	UNIQUE_ACTION_NAMES("8",null),
	PROCESS_INVOCATION_ONLY_IN_PROCESS("9",null),
	RESPONSIBILITIY_INVOCATION_ONLY_IN_PROCESS("10",null),
	OPAQUE_ACTION_ONLY_IN_PROCESS("11",null),
	SEND_OBJECT_ACTION_ONLY_IN_PROCESS("12",null),
	ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS("13",null),
	TARGET_FOR_OPAQUE_ACTIONS("14",null),
	PIN_FOR_PARAMETER("15","No pin found for parameter"),
	MORE_PINS_THAN_PARAMETERS("16","The number of {1} on {0} does not correspond with the number of {2} on the {3} specified");
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
