package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;

abstract public class OJParameterGEN extends OJElement{
	private OJPathName type = null;
	protected OJParameterGEN(String name){
		super(name);
	}
	public OJPathName getType(){
		return type;
	}
	public void setType(OJPathName element){
		if(type != element){
			type = element;
		}
	}
	public OJElement getCopy(){
		OJParameter result = new OJParameter(getName());
		this.copyInfoInto(result);
		return result;
	}
	public void copyInfoInto(OJParameter copy){
		super.copyInfoInto(copy);
		if(getType() != null){
			copy.setType(getType());
		}
	}
}