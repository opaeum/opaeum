package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
@AccessType("field")

@Embeddable()
public class CascadingInterfaceValue extends AbstractInterfaceValue{
	//HACK!! duplicated the state as Hibernate does not seem to handle inheritance in embedabbles
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
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
		this.value=value;
	}

}
