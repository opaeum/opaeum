package org.opaeum.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IPackage;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.core.INakedComment;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedRootObject;

public abstract class EmulatingElement implements INakedElement{
	private static final long serialVersionUID = -1375831977226433661L;
	private String documentation;
	private Map<String,INakedElement> ownedElements = new HashMap<String,INakedElement>();
	public String getDocumentation(){
		return documentation;
	}
	public INakedElement getOwnedElement(String id){
		return getOwnedElementMap().get(id);
	}
	protected Map<String,INakedElement> getOwnedElementMap(){
		return this.ownedElements;
	}
	public void setDocumentation(String documentation){
		this.documentation = documentation;
	}
	protected INakedElement originalElement;
	public INakedElement getOriginalElement(){
		return originalElement;
	}
	public EmulatingElement(INakedElement element){
		super();
		this.originalElement = element;
	}
	public int hashCode(){
		return originalElement.hashCode();
	}
	public boolean equals(Object other){
		if(other instanceof EmulatingElement){
			if(other == this){
				return true;
			}else{
				return ((EmulatingElement) other).getId().equals(getId());
			}
		}else{
			return false;
		}
	}
	public List<INakedComment> getComments(){
		return Collections.emptyList();
	}
	public void addStereotype(INakedInstanceSpecification stereotype){
	}
	public String getId(){
		return originalElement.getId();
	}
	public MappingInfo getMappingInfo(){
		return originalElement.getMappingInfo();
	}
	public String getMetaClass(){
		return originalElement.getMetaClass();
	}
	public INakedNameSpace getNameSpace(){
		return originalElement.getNameSpace();
	}
	public INakedRootObject getNakedRoot(){
		return ((INakedElement) getOwnerElement()).getNakedRoot();
	}
	public IPackage getRoot(){
		return getNakedRoot();
	}
	public INakedElementOwner getOwnerElement(){
		return originalElement.getOwnerElement();
	}
	public INakedInstanceSpecification getStereotype(String name){
		return null;
	}
	public Collection<? extends INakedInstanceSpecification> getStereotypes(){
		return Collections.emptySet();
	}
	@SuppressWarnings("unchecked")
	public <T>T getTaggedValue(String stereotype,String tag){
		return null;
	}
	public boolean hasStereotype(String name){
		return false;
	}
	public boolean hasTaggedValue(String stereotype,String tag){
		return false;
	}
	public void initialize(String id,String name,boolean b){
	}
	public void setMappingInfo(MappingInfo vi){
	}
	public void setName(String umlName){
	}
	public void setOwnerElement(INakedElementOwner element){
	}
	public String getName(){
		return originalElement.getName();
	}
	@SuppressWarnings("deprecation")
	public PathName getPathName(){
		return originalElement.getPathName();
	}
	public Collection<INakedElement> getOwnedElements(){
		return getOwnedElementMap().values();
	}
	public void addOwnedElement(INakedElement element){
		if(element != null){
			getOwnedElementMap().put(element.getId(), element);
		}
	}
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = new HashSet<INakedElement>();
		result.add(element);
		if(element != null){
			getOwnedElementMap().remove(element.getId());
		}
		return result;
	}
	@Override
	public boolean isStoreMappingInfo(){
		return false;
	}
	@Override
	public void markForDeletion(){
	}
	@Override
	public boolean isMarkedForDeletion(){
		return false;
	}
	public INakedRootObject getRootObject(){
		if(getOwnerElement() instanceof INakedElement){
			return ((INakedElement) getOwnerElement()).getRootObject();
		}
		return null;
	}
	@Override
	public Collection<INakedElement> getAllDescendants(){
		return getOwnedElements();
	}
}