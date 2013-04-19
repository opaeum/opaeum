package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
@AccessType("field")

@Embeddable()
public class CascadingInterfaceValue extends AbstractAnyValue {
	//HACK!! duplicated the state as Hibernate does not seem to handle inheritance in embedabbles
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	public String getClassIdentifier(){
		return classIdentifier;
	}
	public void setClassIdentifier(String classIdentifier){
		this.classIdentifier = classIdentifier;
	}
	public IPersistentObject getValue(){
		return value;
	}
	public void setValueInternal(IPersistentObject value){
		this.value = value;
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public Long getIdentifier(){
		return identifier;
	}
	protected boolean shouldCascade(){
		return true;
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return c.getName();
	}
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		try{
			return Class.forName(getClassIdentifier());
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}


}
