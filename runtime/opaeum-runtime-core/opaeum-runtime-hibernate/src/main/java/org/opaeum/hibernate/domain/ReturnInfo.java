package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable
@AccessType("field")
public class ReturnInfo extends AbstractAnyValue implements IAnyValue{
	// HACK!! duplicated the state as Hibernate does not seem to handle
	// inheritance in embedabbles
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;
	public ReturnInfo(){
	}
	public ReturnInfo(IToken t){
		setValue(t);
	}
	public void setIdentifier(Long identifier){
		this.identifier = identifier;
	}
	public Long getIdentifier(){
		return identifier;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public IToken getValue(AbstractPersistence p){
		return (IToken) super.getValue(p);
	}
	@Override
	protected String getClassIdentifier(Class<?> c,JavaMetaInfoMap p){
		return p.getUuidFor(c);
	}
	@Override
	protected Class<?> getClass(String classUuid,JavaMetaInfoMap p){
		return p.getTokenClass(classUuid);
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
