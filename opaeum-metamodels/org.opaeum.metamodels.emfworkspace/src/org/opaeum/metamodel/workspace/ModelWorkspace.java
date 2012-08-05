package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.Set;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.feature.WorkspaceMappingInfo;
import org.opaeum.metamodel.validation.ErrorMap;

public interface ModelWorkspace extends Element{
	ErrorMap getErrorMap();
	Element getModelElement(String id);
	WorkspaceMappingInfo getWorkspaceMappingInfo();
	void setWorkspaceMappingInfo(WorkspaceMappingInfo vim);
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