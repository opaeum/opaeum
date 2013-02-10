package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
@AccessType("field")
@Embeddable()

public class ExternalValue extends AbstractExternalValue{
	String applicationIdentifier;
	String classIdentifier;
	Long identifier;
	@Transient
	IPersistentObject value;
	public String getApplicationIdentifier(){
		return applicationIdentifier;
	}
	public void setApplicationIdentifier(String applicationIdentifier){
		this.applicationIdentifier = applicationIdentifier;
	}
	public String getClassIdentifier(){
		return classIdentifier;
	}
	public void setClassIdentifier(String classIdentifier){
		this.classIdentifier = classIdentifier;
	}
	public Long getIdentifier(){
		return identifier;
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public IPersistentObject getValue(){
		return value;
	}
	public void setValue(IPersistentObject value){
		this.value = value;
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return p.getUuidFor(c);
	}
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		return p.getClass(classUuid);
	}
	@Override
	public String getApplicationIdentifier(Class<? extends IPersistentObject> value){
		return value.getAnnotation(NumlMetaInfo.class).applicationIdentifier();
	}
}
