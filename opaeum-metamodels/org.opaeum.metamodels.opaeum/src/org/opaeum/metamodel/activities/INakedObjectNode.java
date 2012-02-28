package org.opaeum.metamodel.activities;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.core.IModifiableTypedElement;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedValueSpecification;
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
	boolean canAcceptInputFrom(INakedMultiplicityElement from);
	boolean canDeliverOutputTo(INakedMultiplicityElement to);
	INakedValueSpecification getUpperBound();

}
