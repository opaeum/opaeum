package net.sf.nakeduml.metamodel.core;

import java.util.Collection;

public interface INakedRootObject extends INakedPackage{
	String getFileName();
	Collection<INakedRootObject> getDependencies();
}
