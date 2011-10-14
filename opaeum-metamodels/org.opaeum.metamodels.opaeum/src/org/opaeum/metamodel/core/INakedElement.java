package org.opaeum.metamodel.core;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;

import org.opaeum.feature.MappingInfo;
public interface INakedElement extends IModelElement,INakedElementOwner, Serializable {
	INakedRootObject getRootObject();
	@Deprecated
	//Should only be used in Octopus
	
	public PathName getPathName();
	/**
	 * Returns the name of the UML meta class represented
	 */
	String getMetaClass();
	String getId();
	List<INakedComment> getComments();
	INakedElementOwner getOwnerElement();
	void setOwnerElement(INakedElementOwner element);
	void initialize(String id, String name,boolean storeMappingInfo);
	void setMappingInfo(MappingInfo vi);
	MappingInfo getMappingInfo();
	INakedNameSpace getNameSpace();
	void setName(String umlName);
	boolean hasStereotype(String name);
	boolean hasTaggedValue(String stereotype, String tag);
	<T> T getTaggedValue(String stereotype, String tag);
	Collection<? extends INakedInstanceSpecification> getStereotypes();
	void addStereotype(INakedInstanceSpecification stereotype);
	INakedInstanceSpecification getStereotype(String name);
	String getDocumentation();
	void setDocumentation(String d);
	 INakedRootObject getNakedRoot();
	boolean isStoreMappingInfo();
	void markForDeletion();
	boolean isMarkedForDeletion();
	Collection<INakedElement> getAllDescendants();
	
}