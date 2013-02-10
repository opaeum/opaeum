package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
@AccessType("field")

@Embeddable()
public class UiidBasedInterfaceValue extends AbstractAnyValue implements IAnyValue{
	//HACK!! duplicated the state as Hibernate does not seem to handle inheritance in embedabbles
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		return p.getClass(classUuid);
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return p.getUuidFor(c);
	}
	public Long getIdentifier(){
		return identifier;
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public String getClassIdentifier(){
		return classIdentifier;
	}
	public void setClassIdentifier(String classIdentifier){
		this.classIdentifier = classIdentifier;
	}
	public IPersistentObject getValue(){
		return value;
	}
	public void setValue(IPersistentObject value){
		this.value = value;
	}


}
