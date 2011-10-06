package org.opeum.metamodel.models;

import org.opeum.metamodel.core.INakedRootObject;

public interface INakedModel extends INakedRootObject {
	void setViewpoint(String s);
	String getViewpoint();
	boolean isLibrary();
	boolean isRegeneratingLibrary();
	String getImplementationCodeFor(String artifactName);

}
