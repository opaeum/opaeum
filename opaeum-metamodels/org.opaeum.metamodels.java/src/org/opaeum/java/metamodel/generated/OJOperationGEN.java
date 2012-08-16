package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibleElement;

abstract public class OJOperationGEN extends OJVisibleElement{
	private boolean isAbstract = false;
	private OJPathName genericTypeParam = null;
	private List<OJParameter> parameters = new ArrayList<OJParameter>();
	private OJBlock body = null;
	private OJClassifier owner = null;
	private OJPathName returnType = null;
	private Set<OJPathName> throwsExceptions = new HashSet<OJPathName>();
	protected OJOperationGEN(String name){
		super(name);
	}
	public List<OJPathName> getParamTypes(){
		List<OJPathName> result = new ArrayList<OJPathName>();
		return result;
	}
	public boolean isEqual(String name,List<OJPathName> types){
		return true;
	}
	public boolean isAbstract(){
		return isAbstract;
	}
	public void setAbstract(boolean element){
		if(isAbstract != element){
			isAbstract = element;
		}
	}
	public OJPathName getGenericTypeParam(){
		return genericTypeParam;
	}
	public void setGenericTypeParam(OJPathName element){
		if(genericTypeParam != element){
			genericTypeParam = element;
		}
	}
	public List<OJParameter> getParameters(){
		return parameters;
	}
	public void setParameters(List<OJParameter> element){
		if(parameters != element){
			parameters = element;
		}
	}
	public void addToParameters(OJParameter element){
		if(parameters.contains(element)){
			return;
		}
		parameters.add(element);
	}
	public void removeFromParameters(OJParameter element){
		parameters.remove(element);
	}
	public void addToParameters(Collection<OJParameter> newElems){
		for(OJParameter item:newElems){
			addToParameters(item);
		}
	}
	public void removeFromParameters(Collection<OJParameter> oldElems){
		for(OJParameter item:oldElems){
			removeFromParameters(item);
		}
	}
	public void removeAllFromParameters(){
		for(OJParameter item:new ArrayList<OJParameter>(getParameters())){
			removeFromParameters(item);
		}
	}
	public OJBlock getBody(){
		return body;
	}
	public void setBody(OJBlock element){
		if(body != element){
			body = element;
		}
	}
	public void setOwner(OJClassifier element){
		if(this.owner != element){
			if(this.owner != null){
				this.owner.z_internalRemoveFromOperations(((OJOperation) this));
			}
			this.owner = element;
			if(element != null){
				element.z_internalAddToOperations(((OJOperation) this));
			}
		}
	}
	public OJClassifier getOwner(){
		return owner;
	}
	public void z_internalAddToOwner(OJClassifier element){
		this.owner = element;
	}
	public void z_internalRemoveFromOwner(OJClassifier element){
		this.owner = null;
	}
	public OJPathName getReturnType(){
		return returnType;
	}
	public void setReturnType(OJPathName element){
		if(returnType != element){
			returnType = element;
		}
	}
	public Set<OJPathName> getThrows(){
		return throwsExceptions;
	}
	public void setThrows(Set<OJPathName> element){
		if(throwsExceptions != element){
			throwsExceptions = element;
		}
	}
	public void addToThrows(OJPathName element){
		if(throwsExceptions.contains(element)){
			return;
		}
		throwsExceptions.add(element);
	}
	public void removeFromThrows(OJPathName element){
		throwsExceptions.remove(element);
	}
	public String getIdString(){
		String result = "";
		result = super.getIdString();
		return result;
	}
}