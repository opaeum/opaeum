package org.opaeum.linkage;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.metamodel.validation.IValidationRule;

public enum ActivityValidationRule implements IValidationRule{
	AT_LEAST_ONE_DEFAULT_FLOW("1","Action {0} requires at least one flow without a guard condition",pkg.getActivityNode_Outgoing()),
	NO_CONDITIONAL_FLOW_TO_JOIN("3","The conditional flow {0} is not allowed into {1} join {2}",pkg.getActivityNode_Incoming()),
	ONE_FLOW_TO_OBJECT_NODES("4","Only one object flow is allowed into ObjectNode {0}",pkg.getActivityNode_Incoming()),
	ONE_FLOW_FROM_OBJECT_NODES("5","Only one object flow is allowed from ObjectNode {0}",pkg.getActivityNode_Outgoing()),
	LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS("Long running actions can only be used from business processes",
			"{0} is used from {1} which is a method, but should be used from a business process only"),
	ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS("AcceptEventActions can only be used in activities marked as a processes",
			"AcceptEventAction {0} can only be used in activities marked as a processes"),
	// PIN_FOR_PARAMETER("15","No pin found for parameter"),
	OBJECT_NODE_TYPES_MUST_MATCH("17","The type of the {1}, the source of {0}, does not correspond with the type of its target {2}"),
	// SINGLE_OUTGOING_EDGE("17","{0} may only have a single outgoing edge"),
	// EITHER_OBJECT_FLOW_OR_CONTROL_FLOW("17","{0} may only have a single outgoing edge"),
	/**
	 * The selection behavior takes all the tokens in the object node as input and chooses a single token from those
	 */
	SELECTION_RESULT_MULTIPLICITY_MUST_BE_SINGLE_VALUED("18",
			"Edge {0} is invalid: the multiplicity of result {1} of selection behavior {2} must be single valued",pkg
					.getObjectFlow_Selection()),
	/**
	 * The input parameter must be a bag of elements of the same or a supertype of the type of source object node
	 * */
	SELECTION_INPUT_MULTIPLICITY_MUST_BE_BAG("19",
			"Edge {0} is invalid: the multiplicity of input {1} of selection behavior {2} should be multiple,unordered and non-unique",pkg.getObjectFlow_Selection()),
	SELECTION_REQUIRES_OBJECT_SOURCE_NODE("240","Edge {0} is invalid: its can only have selection behavior {1} if it has an Object Node as a source",pkg
			.getObjectFlow_Selection()),
	SELECTION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT("20",
			"Edge {0} is invalid: its selection behavior {1} does not have exactly one input parameter and one result parameter",pkg.getObjectFlow_Selection()),
	SELECTION_RESULT_MUST_CONFORM_TO_TARGET_TYPE("21",
			"Edge {0} is invalid: the type of result {1} of selection behavior {2} does not conform to the type of the target object {3}",pkg
					.getObjectFlow_Selection()),
	SELECTION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE("22",
			"Edge {0} is invalid: the type of input {1} of selection behavior {2} does not conform to the type of the source object {3}",pkg
					.getObjectFlow_Selection()),
	TRANSFORMATION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT("23",
			"Edge{0} is invalid: its transformation behavior {1} does not have exactly one input parameter and one result parameter",pkg
					.getObjectFlow_Transformation()),
	TRANSFORMATION_RESULT_MUST_CONFORM_TO_TARGET_TYPE("24",
			"Edge {0} is invalid: the type of result {1} of transformation behavior {2} does not conform to the type of the target object {3}",pkg
					.getObjectFlow_Transformation()),
	TRANSFORMATION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE("24",
			"Edge {0} is invalid: the type of input {1} of transformation behavior {2} does not conform to the type of the source object {3}",pkg
					.getObjectFlow_Transformation()),
	SELECTION_INPUT_MUST_CONFORM_TO_TRANSFORMATION_RESULT_TYPE("25",
			"Edge {0} is invalid: the type of input {1} of selection behavior {2} does not conform to the type of result {3} of transformation behavior {4}",pkg
					.getObjectFlow_Selection()),
	SELECTION_INPUT_MULTIPLICITY_MUST_CORRESPOND_WITH_TRANSFORMATION_RESULT_MULTIPLICITY(
			"26",
			"Edge {0} is invalid: the multiplicity of input {1} of selection behavior {2} does not conform to the multiplicity of result {3} of transformation behavior {4}",
			pkg.getObjectFlow_Selection()),
	SELECTION_BEHAVIOR_ASSUMES_MULTIPLE_SOURCE_VALUES("26",
			"Source node {1} does not have a multiplicity upper higher than 1, yet edge {0} has a selection behavior {3}",pkg.getObjectFlow_Selection()),
	SOURCE_AND_TARGET_MULTIPLICTY_MUST_CORRESPOND("27",
			"Edge {0} is invalid: the multiplicity of source {1} does not correspond with the multiplicity of source {2}"),
	TRANSFORMATION_ARGUMENT_MULTIPLICITY_MUST_BE_ONE("28","Edge {0} is invalid: the multiplicity of the argument {1} of transformation {2} must be [1..1]",pkg
			.getObjectFlow_Transformation()),
	TRANSFORMATION_RESULT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_TARGET_MULTIPLICITY("28",
			"Edge {0} is invalid: the multiplicity of result {1} on transformation {2} must correspond with the multiplicity of target {3}",pkg
					.getObjectFlow_Transformation()),
	ACTIVITY_EDGE_BROKEN("29","{0} is broken irrecoverably. Please delete it");
	EStructuralFeature[] features;
	ActivityValidationRule(String description,String messagePattern,EStructuralFeature...features){
		this.description = description;
		this.messagePattern = messagePattern;
		this.features = features;
	}
	public String getDescription(){
		return description;
	}
	public EStructuralFeature[] getFeatures(){
		return features;
	}
	public String getMessagePattern(){
		if(messagePattern == null){
			return name();
		}
		return messagePattern;
	}
	private String description;
	private String messagePattern;
}
