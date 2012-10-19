package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.metamodel.validation.ErrorMap;

public interface ModelWorkspace extends Element{
	ErrorMap getErrorMap();
	Element getModelElement(String id);
	OpaeumLibrary getOpaeumLibrary();
	Collection<Package> getRootObjects();
	String getName();
	Set<Package> getGeneratingModelsOrProfiles();
	boolean isPrimaryModel(Package rootObject);
	void setIdentifier(String directoryName);
	String getIdentifier();
	Collection<Package> getPrimaryRootObjects();
	Set<Element> getDependentElements(Element e);
}