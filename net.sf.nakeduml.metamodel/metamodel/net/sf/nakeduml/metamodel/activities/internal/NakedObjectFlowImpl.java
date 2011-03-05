package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;

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
