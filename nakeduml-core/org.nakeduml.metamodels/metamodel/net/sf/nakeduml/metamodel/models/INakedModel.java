package net.sf.nakeduml.metamodel.models;

import net.sf.nakeduml.metamodel.core.INakedRootObject;

public interface INakedModel extends INakedRootObject {
	void setViewpoint(String s);
	String getViewpoint();
	boolean isLibrary();
	boolean isRegeneratingLibrary();
	String getImplementationCodeFor(String artifactName);

}
