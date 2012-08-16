package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJOperation;

abstract public class OJConstructorGEN extends OJOperation{
	private OJClass f_owningClass = null;
	private String delegateConstructor;
	protected OJConstructorGEN(){
		super("");
	}
	public void setOwningClass(OJClass element){
		if(this.f_owningClass != element){
			if(this.f_owningClass != null){
				this.f_owningClass.z_internalRemoveFromConstructors((OJConstructor) ((OJConstructor) this));
			}
			this.f_owningClass = element;
			if(element != null){
				element.z_internalAddToConstructors((OJConstructor) ((OJConstructor) this));
			}
		}
	}
	public OJClass getOwningClass(){
		return f_owningClass;
	}
	public void z_internalAddToOwningClass(OJClass element){
		this.f_owningClass = element;
	}
	public void z_internalRemoveFromOwningClass(OJClass element){
		this.f_owningClass = null;
	}
	public String toString(){
		String result = "";
		result = super.toString();
		return result;
	}
	public String getDelegateConstructor(){
		return delegateConstructor;
	}
	public void setDelegateConstructor(String delegateConstructor){
		this.delegateConstructor = delegateConstructor;
	}
}