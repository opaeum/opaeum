package net.sf.nakeduml.metamodel.workspace;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.mapping.IWorkspaceMappingInfo;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public interface INakedModelWorkspace extends INakedElementOwner{
	ErrorMap getErrorMap();
	void putModelElement(INakedElement mw);
	INakedElement getModelElement(Object id);
	Collection<? extends INakedElement> getElementsOfType(String metaClass);
	Collection<INakedElement> getAllElements();
	INakedEntity getRootUserEntity();
	void setRootUserEntity(INakedEntity rootUserEntity);
	IWorkspaceMappingInfo getWorkspaceMappingInfo();
	void setWorkspaceMappingInfo(IWorkspaceMappingInfo vim);
	MappedTypes getMappedTypes();
	void setBuiltInTypes(MappedTypes types);
	public IOclEngine getOclEngine();
	List<INakedComplexStructure> getClassElementsInDependencyOrder();
	<E extends INakedComplexStructure>List<E> getClasses(Class<E> c);
	Collection<INakedPackage> getChildren();
	void removeElementById(String id);
	String getName();
	
	List<INakedRootObject> getGeneratingModelsOrProfiles();
	void clearGeneratingModelOrProfiles();
	void addGeneratingModelOrProfile(INakedRootObject modelElement);
	boolean isSingleModelWorkspace();
	void setSingleModelWorkspace(boolean singleModelWorkspace);
}