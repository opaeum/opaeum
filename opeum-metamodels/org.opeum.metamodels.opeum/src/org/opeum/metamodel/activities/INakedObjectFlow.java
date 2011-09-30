package org.opeum.metamodel.activities;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
public interface INakedObjectFlow extends INakedActivityEdge {
	INakedObjectNode getOriginatingObjectNode();
	INakedBehavior getTransformation();
	void setTransformation(INakedBehavior b);
	void setSelection(INakedBehavior b);
	INakedBehavior getSelection();
}
