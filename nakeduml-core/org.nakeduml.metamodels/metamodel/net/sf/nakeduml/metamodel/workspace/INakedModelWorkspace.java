package net.sf.nakeduml.metamodel.workspace;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Model;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.mapping.IWorkspaceMappingInfo;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public interface INakedModelWorkspace extends INakedElementOwner {
	ErrorMap getErrorMap();
	void markDependency(INakedClassifier from, INakedClassifier to);
		
	void putModelElement(INakedElement mw);

	INakedElement getModelElement(Object id);

	Collection<? extends INakedElement> getElementsOfType(String metaClass);

	Collection<INakedElement> getAllElements();

	IWorkspaceMappingInfo getWorkspaceMappingInfo();

	void setWorkspaceMappingInfo(IWorkspaceMappingInfo vim);

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
	Set<INakedClassifier> getDependentClassifiers(INakedClassifier e);

}