package org.opeum.metamodel.core;
import java.util.Collection;

import org.opeum.feature.MappingInfo;
public interface INakedElementOwner {
	MappingInfo getMappingInfo();
	Collection<? extends INakedElement> getOwnedElements();
	void setName(String string);
	void addOwnedElement(INakedElement element);
	void removeOwnedElement(INakedElement element, boolean recursively);
	String getMetaClass();
	String getName();
}
