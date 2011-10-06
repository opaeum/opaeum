package org.opaeum.metamodel.activities.internal;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedControlNode;
public class NakedControlNodeImpl extends NakedActivityNodeImpl implements INakedControlNode {
	private static final long serialVersionUID = -4774558296787039182L;
	private ControlNodeType controlNodeType;
	public ControlNodeType getControlNodeType() {
		return this.controlNodeType;
	}
	public void setControlNodeType(ControlNodeType nt) {
		this.controlNodeType = nt;
	}
	@Override
	public boolean isImplicitDecision() {
		if (getControlNodeType().isDecisionNode() || getControlNodeType().isForkNode()) {
			return false;
		} else {
			return super.isImplicitDecision();
		}
	}
	@Override
	public boolean isImplicitFork() {
		if (getControlNodeType().isDecisionNode() || getControlNodeType().isForkNode()) {
			return false;
		} else {
			return super.isImplicitFork();
		}
	}
	@Override
	public boolean isImplicitJoin() {
		if (getControlNodeType().isMergeNode() || getControlNodeType().isJoinNode()) {
			return false;
		} else {
			return super.isImplicitJoin();
		}
	}
}
