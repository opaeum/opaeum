package org.opaeum.metamodel.activities.internal;

import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;

public class NakedObjectFlowImpl extends NakedActivityEdgeImpl implements INakedObjectFlow {
	private static final long serialVersionUID = 6481759202136150887L;
	private INakedBehavior transformation;
	private INakedBehavior selection;

	@Override
	public INakedActivityNode getEffectiveTarget() {
		if (getTarget() instanceof INakedExpansionNode) {
			INakedExpansionNode target = (INakedExpansionNode) getTarget();
			if (target.isInputElement()) {
				return target.getExpansionRegion();
			} else {
				return target;
			}
		}
		if (getTarget() instanceof INakedInputPin) {
			return (INakedActivityNode) getTarget().getOwnerElement();
		} else {
			return getTarget();
		}
	}

	public INakedBehavior getTransformation() {
		return this.transformation;
	}

	public void setTransformation(INakedBehavior transformation) {
		this.transformation = transformation;
	}

	public INakedBehavior getSelection() {
		return selection;
	}

	public void setSelection(INakedBehavior selection) {
		this.selection = selection;
	}

	@Override
	public INakedActivityNode getEffectiveSource() {
		if (getSource() instanceof INakedExpansionNode) {
			INakedExpansionNode source = (INakedExpansionNode) getSource();
			if (source.isOutputElement()) {
				return source.getExpansionRegion();
			} else {
				return source;
			}
		} else if (getSource() instanceof INakedOutputPin) {
			return (INakedActivityNode) getSource().getOwnerElement();
		} else {
			return getSource();
		}
	}

	@Override
	public INakedObjectNode getOriginatingObjectNode() {
		if(getSource() instanceof INakedObjectNode){
			return (INakedObjectNode) getSource();
		}else{
			for (INakedActivityEdge edge : getSource().getAllEffectiveIncoming()) {
				if(edge instanceof INakedObjectFlow){
					//TODO add validation for cases where multilple flows of different types are present 
					//TODO add validation for cases no incoming object flows are present 
					return ((INakedObjectFlow) edge).getOriginatingObjectNode();
				}
			}
		}
		return null;
	}
}
