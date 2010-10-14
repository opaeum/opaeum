package net.sf.nakeduml.metamodel.activities.internal;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
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
