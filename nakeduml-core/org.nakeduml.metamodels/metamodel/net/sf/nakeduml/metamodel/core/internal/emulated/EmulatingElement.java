package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.MappingInfo;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IPackage;

public class EmulatingElement implements INakedElement{
	String documentation;
	public String getDocumentation(){
		return documentation;
	}
	public void setDocumentation(String documentation){
		this.documentation = documentation;
	}
	protected INakedElement originalElement;
	public INakedElement getOriginalElement(){
		return originalElement;
	}
	private List<INakedComment> comments = new ArrayList<INakedComment>();
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
				return this.originalElement.equals(((EmulatingElement) other).originalElement);
			}
		}else{
			return false;
		}
	}
	public List<INakedComment> getComments(){
		return comments;
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
		return originalElement.getStereotype(name);
	}
	public Collection<? extends INakedInstanceSpecification> getStereotypes(){
		return originalElement.getStereotypes();
	}
	public <T>T getTaggedValue(String stereotype,String tag){
		Object o = originalElement.getTaggedValue(stereotype, tag);
		return (T) o;
	}
	public boolean hasStereotype(String name){
		return originalElement.hasStereotype(name);
	}
	public boolean hasTaggedValue(String stereotype,String tag){
		return originalElement.hasTaggedValue(stereotype, tag);
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
	public PathName getPathName(){
		return originalElement.getPathName();
	}
	public Collection<? extends INakedElement> getOwnedElements(){
		return originalElement.getOwnedElements();
	}
	public void addOwnedElement(INakedElement element){
	}
	public void removeOwnedElement(INakedElement element){
	}
	@Override
	public boolean isStoreMappingInfo(){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void markForDeletion(){
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isMarkedForDeletion(){
		// TODO Auto-generated method stub
		return false;
	}
	public INakedRootObject getRootObject(){
		if(getOwnerElement() instanceof INakedElement){
			return ((INakedElement)getOwnerElement()).getRootObject();
		}
		return null;
	}

}