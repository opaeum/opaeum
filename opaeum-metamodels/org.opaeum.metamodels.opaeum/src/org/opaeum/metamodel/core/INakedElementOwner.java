package org.opaeum.metamodel.core;
import java.util.Collection;

import org.opaeum.feature.MappingInfo;
public interface INakedElementOwner {
	MappingInfo getMappingInfo();
	Collection<INakedElement> getOwnedElements();
	void setName(String string);
	void addOwnedElement(INakedElement element);
	Collection<INakedElement> removeOwnedElement(INakedElement element, boolean recursively);
	String getMetaClass();
	String getName();
}
