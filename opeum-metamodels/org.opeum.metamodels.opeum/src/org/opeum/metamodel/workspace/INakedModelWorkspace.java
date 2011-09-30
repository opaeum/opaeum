package org.opeum.metamodel.workspace;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opeum.feature.WorkspaceMappingInfo;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.validation.ErrorMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public interface INakedModelWorkspace extends INakedElementOwner{
	ErrorMap getErrorMap();
	void markDependency(INakedElement from,INakedElement to);
	void putModelElement(INakedElement mw);
	INakedElement getModelElement(Object id);
	Collection<INakedElement> getAllElements();
	WorkspaceMappingInfo getWorkspaceMappingInfo();
	void setWorkspaceMappingInfo(WorkspaceMappingInfo vim);
	OpeumLibrary getOpeumLibrary();
	public IOclEngine getOclEngine();
	Collection<INakedRootObject> getRootObjects();
	String getName();
	List<INakedRootObject> getGeneratingModelsOrProfiles();
	void clearGeneratingModelOrProfiles();
	void addGeneratingRootObject(INakedRootObject modelElement);
	boolean isPrimaryModel(INakedRootObject rootObject);
	void addPrimaryModel(INakedRootObject rootObject);
	void setIdentifier(String directoryName);
	String getIdentifier();
	Collection<INakedRootObject> getPrimaryRootObjects();
	Set<INakedElement> getDependentElements(INakedElement e);
	void removeModelElement(INakedElement mw);
}