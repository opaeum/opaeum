package org.opaeum.metamodel.activities;

import org.opaeum.metamodel.core.INakedClassifier;

public interface INakedControlNode extends INakedActivityNode {
	ControlNodeType getControlNodeType();
	void setControlNodeType(ControlNodeType nt);
	INakedClassifier getOriginatingObjectNodeClassifier();
	boolean hasIncomingObjectFlow();
}
