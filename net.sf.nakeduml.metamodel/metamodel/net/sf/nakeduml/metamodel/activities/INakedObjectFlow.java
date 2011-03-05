package net.sf.nakeduml.metamodel.activities;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
public interface INakedObjectFlow extends INakedActivityEdge {
	INakedObjectNode getOriginatingObjectNode();
	INakedBehavior getTransformation();
	void setTransformation(INakedBehavior b);
	void setSelection(INakedBehavior b);
	INakedBehavior getSelection();
}
