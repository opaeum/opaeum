package net.sf.nakeduml.metamodel.core;
import java.util.Collection;

import net.sf.nakeduml.feature.MappingInfo;
public interface INakedElementOwner {
	MappingInfo getMappingInfo();
	Collection<? extends INakedElement> getOwnedElements();
	void setName(String string);
	void addOwnedElement(INakedElement element);
	void removeOwnedElement(INakedElement element, boolean recursively);
	String getMetaClass();
	String getName();
}
