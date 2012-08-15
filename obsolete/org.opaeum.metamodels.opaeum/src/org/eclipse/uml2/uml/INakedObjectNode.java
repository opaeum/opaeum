package org.eclipse.uml2.uml;
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
