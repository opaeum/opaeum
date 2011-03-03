package net.sf.nakeduml.metamodel.core;
import java.util.Collection;

import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
public interface INakedElementOwner {
	IMappingInfo getMappingInfo();
	Collection<? extends INakedElement> getOwnedElements();
	void setName(String string);
	void addOwnedElement(INakedElement element);
	void removeOwnedElement(INakedElement element);
	String getMetaClass();
	String getName();
}
