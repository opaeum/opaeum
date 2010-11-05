package net.sf.nakeduml.metamodel.activities.internal;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
public class NakedObjectFlowImpl extends NakedActivityEdgeImpl implements INakedObjectFlow {
	private static final long serialVersionUID = 6481759202136150887L;
	private INakedBehavior transformation;
	private INakedBehavior selection;
	@Override
	public INakedActivityNode getEffectiveTarget() {
		if (getTarget() instanceof INakedInputPin || getTarget() instanceof INakedOutputPin || getTarget() instanceof INakedExpansionNode) {
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
		if (getSource() instanceof INakedInputPin || getSource() instanceof INakedOutputPin) {
			return (INakedActivityNode) getTarget().getOwnerElement();
		} else {
			return getTarget();
		}
	}
}
