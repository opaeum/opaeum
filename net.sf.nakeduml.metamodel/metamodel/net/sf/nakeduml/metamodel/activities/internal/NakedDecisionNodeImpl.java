package net.sf.nakeduml.metamodel.activities.internal;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedDecisionNode;
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
