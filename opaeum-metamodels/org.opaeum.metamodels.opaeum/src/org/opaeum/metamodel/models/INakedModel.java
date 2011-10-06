package org.opaeum.metamodel.models;

import org.opaeum.metamodel.core.INakedRootObject;

public interface INakedModel extends INakedRootObject {
	void setViewpoint(String s);
	String getViewpoint();
	boolean isLibrary();
	boolean isRegeneratingLibrary();
	String getImplementationCodeFor(String artifactName);

}
