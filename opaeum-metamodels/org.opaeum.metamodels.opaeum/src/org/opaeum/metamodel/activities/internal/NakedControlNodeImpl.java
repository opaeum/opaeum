package org.opaeum.metamodel.activities.internal;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
public class NakedControlNodeImpl extends NakedActivityNodeImpl implements INakedControlNode {
	private static final long serialVersionUID = -4774558296787039182L;
	private ControlNodeType controlNodeType;
	
	
	public NakedControlNodeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	@Override
	public INakedClassifier getOriginatingObjectNodeClassifier() {
		INakedClassifier result = null;
		for (INakedActivityEdge incoming : getIncoming()) {
			if (!(incoming instanceof INakedObjectFlow)) {
				return null;
			} else {
				INakedObjectFlow objectFlow = (INakedObjectFlow)incoming;
				if (result==null) {
					result = objectFlow.getOriginatingObjectNodeClassifier();
				} else {
					INakedClassifier tmp = objectFlow.getOriginatingObjectNodeClassifier();
					if (tmp != result) {
						return null;
					}
				}
			}
		}
		return result;
	}
	@Override
	public INakedMultiplicity getTinkerOriginatingMultiplicity() {
		INakedMultiplicity result = null;
		for (INakedActivityEdge incoming : getIncoming()) {
			if (incoming instanceof INakedObjectFlow) {
				INakedObjectFlow objectFlow = (INakedObjectFlow)incoming;
				result = objectFlow.getTinkerOriginatingMultiplicity();
				if (result.isMany()) {
					return result;
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean hasIncomingObjectFlow() {
		for (INakedActivityEdge incoming : getIncoming()) {
			if (incoming instanceof INakedObjectFlow) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean hasIncomingControlFlow() {
		for (INakedActivityEdge incoming : getIncoming()) {
			if (!(incoming instanceof INakedObjectFlow)) {
				return true;
			}
		}
		return false;
	}
}
