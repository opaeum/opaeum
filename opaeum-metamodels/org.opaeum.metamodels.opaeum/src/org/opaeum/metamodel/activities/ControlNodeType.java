package org.opaeum.metamodel.activities;
import java.io.Serializable;
public enum ControlNodeType implements Serializable {
	DECISION_NODE,
	MERGE_NODE,
	JOIN_NODE,
	FORK_NODE,
	INITIAL_NODE,
	ACTIVITY_FINAL_NODE,
	FLOW_FINAL_NODE ;
	public boolean isDecisionNode() {
		return this == DECISION_NODE;
	}
	public boolean isMergeNode() {
		return this == MERGE_NODE;
	}
	public boolean isForkNode() {
		return this == FORK_NODE;
	}
	public boolean isJoinNode() {
		return this == JOIN_NODE;
	}
	public boolean isInitialNode() {
		return this == INITIAL_NODE;
	}
	public boolean isActivityFinalNode() {
		return this == ACTIVITY_FINAL_NODE;
	}
	public boolean isFlowFinalNode() {
		return this == FLOW_FINAL_NODE;
	}
	public boolean isFinalNode() {
		return isFlowFinalNode() || isActivityFinalNode();
	}
}
