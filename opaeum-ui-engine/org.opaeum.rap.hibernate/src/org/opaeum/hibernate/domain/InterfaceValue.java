package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable()
public class InterfaceValue{
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	public Long getIdentifier(){
		return identifier;
	}
	public IPersistentObject getValue(AbstractPersistence p){
		if(hasValue() && (value==null)){
			Class<?> implementationClass = getImplementationClass();
			value=(IPersistentObject) p.getReference(implementationClass, identifier);
		}
		return value;
	}
	public void setValue(IPersistentObject value){
		if(value == null){
			identifier = null;
			classIdentifier = null;
		}else{
			identifier = value.getId();
			classIdentifier = Environment.getInstance().getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(value.getClass()));
		}
		this.value=value;
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public boolean hasValue(){
		return classIdentifier != null && (identifier != null || value!=null);
	}
	private Class<?> getImplementationClass(){
		if(classIdentifier == null){
			return null;
		}else{
			return Environment.getInstance().getMetaInfoMap().getClass(classIdentifier);
		}
	}
}
