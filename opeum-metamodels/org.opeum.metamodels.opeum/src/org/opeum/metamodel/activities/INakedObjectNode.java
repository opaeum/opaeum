package org.opeum.metamodel.activities;
import org.opeum.metamodel.actions.INakedExceptionHandler;
import org.opeum.metamodel.core.IModifiableTypedElement;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedMultiplicity;
public interface INakedObjectNode extends IModifiableTypedElement, INakedActivityNode {
	void setBaseType(INakedClassifier nakedPeer);
	void setIsOrdered(boolean ordered);
	void setIsUnique(boolean unique);
	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);
	ObjectNodeType getObjectNodeType();
	int getIndex();
	void setIndex(int i);
	INakedObjectNode getFeedingNode();
	INakedObjectNode getFedNode();
	INakedExceptionHandler getIncomingExceptionHandler();
	void setIncomingExceptionHandler(INakedExceptionHandler incomingExceptionHandler);


}
