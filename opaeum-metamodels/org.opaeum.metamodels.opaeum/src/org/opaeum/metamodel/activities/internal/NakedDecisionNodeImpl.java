package org.opaeum.metamodel.activities.internal;
import org.eclipse.uml2.uml.ControlNodeType;
import org.eclipse.uml2.uml.INakedDecisionNode;
public class NakedDecisionNodeImpl extends NakedControlNodeImpl implements INakedDecisionNode {
	private static final long serialVersionUID = -6645818923798630608L;
	@Override
	public String getMetaClass() {
		return "desicionNode";
	}
	@Override
	public ControlNodeType getControlNodeType() {
		return ControlNodeType.DECISION_NODE;
	}
}
