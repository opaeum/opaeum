package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable
@AccessType("field")

public class InterfaceValue extends AbstractInterfaceValue{
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	public InterfaceValue(IPersistentObject resource,JavaMetaInfoMap env){
		setValue(resource,env);
	}
	public InterfaceValue(){
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public Long getIdentifier(){
		return identifier;
	}
	protected IPersistentObject getValue(){
		return value;
	}
	protected String getClassIdentifier(){
		return classIdentifier;
	}
	protected void setClassIdentifier(String classIdentifier){
		this.classIdentifier = classIdentifier;
	}
	protected void setValueImpl(IPersistentObject value){
		this.value = value;
	}
}
