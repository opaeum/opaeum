package org.opaeum.metamodel.activities;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
public interface INakedObjectFlow extends INakedActivityEdge {
	INakedObjectNode getOriginatingObjectNode();
	INakedBehavior getTransformation();
	void setTransformation(INakedBehavior b);
	void setSelection(INakedBehavior b);
	INakedBehavior getSelection();
	INakedObjectNode getFedObjectNode();
	INakedClassifier getOriginatingObjectNodeClassifier();
	INakedMultiplicity getTinkerOriginatingMultiplicity();
}
