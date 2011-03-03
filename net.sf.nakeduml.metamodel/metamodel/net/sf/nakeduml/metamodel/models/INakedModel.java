package net.sf.nakeduml.metamodel.models;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedRootObject;

public interface INakedModel extends INakedRootObject {
	void setViewpoint(String s);

	String getViewpoint();

}
