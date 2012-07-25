package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.feature.WorkspaceMappingInfo;
import org.opaeum.metamodel.validation.ErrorMap;

public interface ModelWorkspace extends Element{
	ErrorMap getErrorMap();
	void markDependency(Element from,Element to);
	void putModelElement(Element mw);
	Element getModelElement(Object id);
	Collection<Element> getAllElements();
	WorkspaceMappingInfo getWorkspaceMappingInfo();
	void setWorkspaceMappingInfo(WorkspaceMappingInfo vim);
	OpaeumLibrary getOpaeumLibrary();
	Collection<Package> getRootObjects();
	String getName();
	Set<Package> getGeneratingModelsOrProfiles();
	void clearGeneratingModelOrProfiles();
	void addGeneratingRootObject(Package modelElement);
	boolean isPrimaryModel(Package rootObject);
	void addPrimaryModel(Package rootObject);
	void setIdentifier(String directoryName);
	String getIdentifier();
	Collection<Package> getPrimaryRootObjects();
	Set<Element> getDependentElements(Element e);
	void removeModelElement(Element mw);
	void clearRootClassifiers();
	void addRootClassifier(Classifier cp);
	Collection<Classifier> getRootClassifiers();
	void release();
	Classifier getApplicationRoot();
	void setApplicationRoot(Classifier  root);
	public abstract Collection<Model> getPrimaryModels();
}