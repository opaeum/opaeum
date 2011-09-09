package net.sf.nakeduml.metamodel.activities;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.core.IModifiableTypedElement;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
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
