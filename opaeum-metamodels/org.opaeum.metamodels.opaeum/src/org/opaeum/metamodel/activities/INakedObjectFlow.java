package org.opaeum.metamodel.activities;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
public interface INakedObjectFlow extends INakedActivityEdge {
	INakedObjectNode getOriginatingObjectNode();
	INakedBehavior getTransformation();
	void setTransformation(INakedBehavior b);
	void setSelection(INakedBehavior b);
	INakedBehavior getSelection();
	INakedObjectNode getFedObjectNode();
}
