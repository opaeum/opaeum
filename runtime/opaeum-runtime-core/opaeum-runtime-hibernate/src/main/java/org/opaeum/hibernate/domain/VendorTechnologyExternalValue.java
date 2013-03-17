package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

@AccessType("field")
@Embeddable()
public class VendorTechnologyExternalValue extends AbstractExternalValue{
	String classIdentifier;
	Long identifier;
	@Transient
	IPersistentObject value;
	public IPersistentObject getValue(){
		return value;
	}
	public void setValueInternal(IPersistentObject value){
		this.value = value;
	}
	@Override
	public String getApplicationIdentifier(){
		
		String[] split = getClassIdentifier().split("\\.");
		return split[3] + "_" + split[4];
	}
	@Override
	public void setApplicationIdentifier(String v){
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return c.getName();
	}
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		try{
			return Class.forName(classUuid);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
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
	@Override
	public String getApplicationIdentifier(Class<? extends IPersistentObject> cls){
		String[] split = cls.getName().split("\\.");
		return split[3] + "_" + split[4];
	}
}
