package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

@Embeddable
@AccessType("field")
public class InterfaceValue extends AbstractAnyValue implements IAnyValue{
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	public InterfaceValue(){
	}
	public InterfaceValue(IPersistentObject resource){
		setValueInternal(resource);
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public Long getIdentifier(){
		return identifier;
	}
	public IPersistentObject getValue(){
		return value;
	}
	public String getClassIdentifier(){
		return classIdentifier;
	}
	public void setClassIdentifier(String classIdentifier){
		this.classIdentifier = classIdentifier;
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
	@Override
	public void setValueInternal(IPersistentObject v){
		this.value=v;
	}
}
