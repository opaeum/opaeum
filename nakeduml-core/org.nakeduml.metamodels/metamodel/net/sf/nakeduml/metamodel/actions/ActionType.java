package net.sf.nakeduml.metamodel.actions;

import java.io.Serializable;

public enum ActionType implements Serializable{
	SEND_SIGNAL_ACTION,
	CALL_OPERATION_ACTION,
	ACCEPT_SIGNAL_EVENT_ACTION,
	ACCEPT_CALL_EVENT_ACTION,
	ACCEPT_TIME_EVENT_ACTION,
	WRITE_STRUCTURAL_FEATURE_ACTION,
	SEND_OBJECT_ACTION,
	CREATE_OBJECT_ACTION,
	START_CLASSIFIER_BEHAVIOR_ACTION,
	READ_STRUCTURAL_FEATURE_ACTION,
	CALL_BEHAVIOR_ACTION,
	OPAQUE_ACTION,
	READ_VARIABLE_ACTION,
	CLEAR_VARIABLE_ACTION,
	ADD_VARIABLE_VALUE_ACTION, REMOVE_VARIABLE_VALUE_ACTION, RAISE_EXCEPTION_ACTION, STRUCTURED_ACTIVITY_NODE
	;
	public boolean isCallAction(){
		return isCallOperationAction() || isCallBehaviorAction();
	}
	private boolean isCallBehaviorAction(){
		return this == CALL_BEHAVIOR_ACTION;
	}
	public boolean isSendSignalAction(){
		return this == SEND_SIGNAL_ACTION;
	}
	public boolean isCallOperationAction(){
		return this == CALL_OPERATION_ACTION;
	}
	public boolean isAcceptSignalAction(){
		return this == ACCEPT_SIGNAL_EVENT_ACTION;
	}
	public boolean isAcceptCallEventAction(){
		return this == ACCEPT_CALL_EVENT_ACTION;
	}
	public boolean isAcceptTimeEventAction(){
		return this == ACCEPT_TIME_EVENT_ACTION;
	}
	public boolean isSendObjectAction(){
		return this == SEND_OBJECT_ACTION;
	}
	public boolean isCreateObjectAction(){
		return this == SEND_OBJECT_ACTION;
	}
	public boolean isWriteStructuralFeatureAction(){
		return this == WRITE_STRUCTURAL_FEATURE_ACTION;
	}
	public boolean isReadStructuralFeatureAction(){
		return this == READ_STRUCTURAL_FEATURE_ACTION;
	}
	public boolean isAcceptEventAction(){
		return isAcceptSignalAction() || isAcceptTimeEventAction() || isAcceptCallEventAction();
	}
}
