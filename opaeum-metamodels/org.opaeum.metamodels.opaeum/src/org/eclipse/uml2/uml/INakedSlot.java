package org.eclipse.uml2.uml;
import java.util.List;
public interface INakedSlot extends INakedElement, INakedElementOwner {
	INakedInstanceSpecification getOwningInstance();
	INakedProperty getDefiningFeature();
	void setDefiningFeature(INakedProperty property);
	INakedValueSpecification getFirstValue();
	List<INakedValueSpecification> getValues();
}
