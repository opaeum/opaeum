package org.opaeum.metamodel.core.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import nl.klasse.octopus.expressions.internal.types.PathName;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedComment;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedNameSpace;
import org.eclipse.uml2.uml.INakedRootObject;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.workspace.ModelWorkspace;

/**
 * @author Ampie Barnard
 */
public abstract class NakedElementImpl extends NakedElementOwnerImpl implements Serializable,INakedElement{
	protected List<INakedComment> comments = new ArrayList<INakedComment>();
	@Override
	public void addOwnedElement(INakedElement element){
		if(element instanceof INakedComment){
			comments.add((INakedComment) element);
		}
		super.addOwnedElement(element);
	}
	@Override
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedComment){
			comments.remove(element);
		}else if(element instanceof INakedInstanceSpecification){
			INakedInstanceSpecification sa = (INakedInstanceSpecification) element;
			if(stereotypes.containsKey(sa.getName())){
				stereotypes.remove(sa.getName());
			}
		}
		return result;
	}
	private boolean markedForDeletion;
	String uuid;
	private static final long serialVersionUID = -825314743586339864L;
	/**
	 * A The ID of this originalElement's namespace as supplied by the modelling tool of choice The Modelelement class will resolve the links
	 * itself. This should make it easier to populate the Modelelement from various sources
	 */
	protected String id;
	Map<String,INakedInstanceSpecification> stereotypes = new HashMap<String,INakedInstanceSpecification>();
	private INakedElementOwner ownerElement;
	private String documentation;
	private boolean storeMappingInfo;
	public NakedElementImpl(){
		counts.put(getClass(), getCount() + 1);
	}
	public String getDocumentation(){
		return documentation;
	}
	public String getUuid(){
		if(uuid == null){
			uuid = UUID.randomUUID().toString();
		}
		return uuid;
	}
	public INakedRootObject getNakedRoot(){
		return ((INakedElement) getOwnerElement()).getNakedRoot();
	}
	public void setDocumentation(String documentation){
		this.documentation = documentation;
	}
	public List<INakedComment> getComments(){
		return comments;
	}
	public Collection<INakedInstanceSpecification> getStereotypes(){
		return this.stereotypes.values();
	}
	/**
	 * This method is used only by Octopus ocl generation. Uses the qualifiedJavaName
	 */
	@SuppressWarnings("deprecation")
	public PathName getPathName(){
		PathName result = null;
		if(getMappingInfo().getQualifiedJavaName() == null){
			if(getOwnerElement() instanceof INakedElement){
				result = ((INakedElement) getOwnerElement()).getPathName().getCopy();
				result.addString(getName());
			}else{
				result = new PathName(getName());
			}
		}else{
			result = new PathName();
			StringTokenizer st = new StringTokenizer(getMappingInfo().getQualifiedJavaName(), ".");
			while(st.hasMoreElements()){
				String s = (String) st.nextElement();
				if(s.length() > 0){
					result.addString(s);
				}
			}
		}
		return result;
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof NakedElementImpl)){
			return false;
		}else if(this == obj){
			return true;
		}else{
			INakedElement other = (INakedElement) obj;
			return getId().equals(other.getId());
		}
	}
	public void initialize(String id,String name,boolean storeMappingInfo){
		this.id = id;
		this.setName(name == null || name.trim().length() == 0 ? null : name);
		this.storeMappingInfo = storeMappingInfo;
	}
	public boolean isStoreMappingInfo(){
		return storeMappingInfo;
	}
	public void setMappingInfo(MappingInfo vi){
		this.mappingInfo = vi;
	}
	public MappingInfo getMappingInfo(){
		return this.mappingInfo;
	}
	@Override
	public String toString(){
		return getClass().getSimpleName() + "[" + getName() + "]";
	}
	public INakedNameSpace getNameSpace(){
		INakedElement e = (INakedElement) this.ownerElement;
		while(e != null && !(e instanceof INakedNameSpace)){
			e = (INakedElement) e.getOwnerElement();
		}
		return (INakedNameSpace) e;
	}
	public final String getId(){
		return this.id;
	}
	public boolean hasStereotype(String name){
		return this.stereotypes.containsKey(name);
	}
	public void addStereotype(INakedInstanceSpecification stereotype){
		this.ownedElements.remove(stereotype.getId());
		this.ownedElements.put(stereotype.getId(), stereotype);
		stereotype.setOwnerElement(this);
		this.stereotypes.put(stereotype.getName(), stereotype);
	}
	public INakedInstanceSpecification getStereotype(String name){
		return this.stereotypes.get(name);
	}
	public boolean hasTaggedValue(String stereotype,String tag){
		INakedInstanceSpecification s = getStereotype(stereotype);
		return s != null && s.hasValueForFeature(tag);
	}
	@SuppressWarnings("unchecked")
	public <T>T getTaggedValue(String stereotype,String tag){
		INakedInstanceSpecification s = getStereotype(stereotype);
		if(s != null && s.hasValueForFeature(tag)){
			INakedValueSpecification vs = s.getFirstValueFor(tag);
			return (T) vs.getValue();
		}else{
			return null;
		}
	}
	public INakedElementOwner getOwnerElement(){
		return this.ownerElement;
	}
	public void setOwnerElement(INakedElementOwner ownerElement){
		this.ownerElement = ownerElement;
	}
	@Override
	public int hashCode(){
		if(id == null){
		}
		return id.hashCode();
	}
	public INakedClassifier getNearestClassifier(){
		INakedElementOwner o = getOwnerElement();
		while(!(o instanceof INakedClassifier)){
			if(o == null || o instanceof ModelWorkspace){
				return null;
			}
			o = ((INakedElement) o).getOwnerElement();
		}
		return (INakedClassifier) o;
	}
	@Override
	public boolean isMarkedForDeletion(){
		return markedForDeletion;
	}
	@Override
	public void markForDeletion(){
		this.markedForDeletion = true;
		for(INakedElement e:getOwnedElements()){
			e.markForDeletion();
		}
	}
	public INakedRootObject getRootObject(){
		if(getOwnerElement() instanceof INakedElement){
			return ((INakedElement) getOwnerElement()).getRootObject();
		}
		return null;
	}
}
