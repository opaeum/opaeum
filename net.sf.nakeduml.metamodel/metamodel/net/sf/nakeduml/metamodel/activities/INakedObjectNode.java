package net.sf.nakeduml.metamodel.activities;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
public interface INakedObjectNode extends INakedTypedElement, INakedActivityNode {
	void setBaseType(INakedClassifier nakedPeer);
	void setIsOrdered(boolean ordered);
	void setIsUnique(boolean unique);
	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);
	ObjectNodeType getObjectNodeType();
	int getIndex();
	void setIndex(int i);
	INakedObjectNode getFeedingNode();
	INakedObjectNode getFedNode();

}
