package net.sf.nakeduml.metamodel.workspace;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.WorkspaceMappingInfo;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public interface INakedModelWorkspace extends INakedElementOwner {
	ErrorMap getErrorMap();
	void markDependency(INakedElement from, INakedElement to);
		
	void putModelElement(INakedElement mw);

	INakedElement getModelElement(Object id);

	<T extends INakedElement> Set<T> getElementsOfType(Class<T> c);

	Collection<INakedElement> getAllElements();

	WorkspaceMappingInfo getWorkspaceMappingInfo();

	void setWorkspaceMappingInfo(WorkspaceMappingInfo vim);

	NakedUmlLibrary getNakedUmlLibrary();


	public IOclEngine getOclEngine();

	List<INakedComplexStructure> getClassElementsInDependencyOrder();

	<E extends INakedComplexStructure> List<E> getClasses(Class<E> c);

	Collection<INakedRootObject> getRootObjects();

	void removeElementById(String id);

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

}