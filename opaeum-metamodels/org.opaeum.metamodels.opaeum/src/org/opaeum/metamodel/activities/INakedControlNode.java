package org.opaeum.metamodel.activities;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;

public interface INakedControlNode extends INakedActivityNode {
	ControlNodeType getControlNodeType();
	void setControlNodeType(ControlNodeType nt);
	INakedClassifier getOriginatingObjectNodeClassifier();
	INakedMultiplicity getTinkerOriginatingMultiplicity();
	boolean hasIncomingObjectFlow();
	boolean hasIncomingControlFlow();
}
