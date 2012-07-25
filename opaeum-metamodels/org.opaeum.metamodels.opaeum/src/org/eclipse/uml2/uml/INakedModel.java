package org.eclipse.uml2.uml;


public interface INakedModel extends INakedRootObject {
	void setViewpoint(String s);
	String getViewpoint();
	boolean isLibrary();
	boolean isRegeneratingLibrary();
	String getImplementationCodeFor(String artifactName);

}
