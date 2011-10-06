package org.opaeum.metamodel.core;
import java.util.List;
public interface INakedSlot extends INakedElement, INakedElementOwner {
	INakedInstanceSpecification getOwningInstance();
	INakedProperty getDefiningFeature();
	void setDefiningFeature(INakedProperty property);
	INakedValueSpecification getFirstValue();
	List<INakedValueSpecification> getValues();
}
