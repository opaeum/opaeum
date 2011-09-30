package org.opeum.metamodel.core;
import java.util.List;
public interface INakedInstanceSpecification extends INakedPackageableElement {
	void setClassifier(INakedClassifier c);
	INakedClassifier getClassifier();
	List<INakedSlot> getSlots();
	INakedValueSpecification getFirstValueFor(String attribute);
	boolean hasValueForFeature(String attribute);
	INakedValueSpecification getSpecification();
	INakedSlot getSlotForFeature(String name);
}
