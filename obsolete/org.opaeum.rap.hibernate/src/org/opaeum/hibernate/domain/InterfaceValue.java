package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable()
public class InterfaceValue{
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;

	public IPersistentObject getValue(AbstractPersistence p){
		if(hasValue() && (getValue()==null)){
			Class<?> implementationClass = getImplementationClass();
			setValueImpl((IPersistentObject) p.getReference(implementationClass, getIdentifier()));
		}
		return getValue();
	}
	public void setValue(IPersistentObject value){
		if(value == null){
			setIdentifier(null);
			setClassIdentifier(null);
		}else{
			setIdentifier(value.getId());
			setClassIdentifier(IntrospectionUtil.getOriginalClass(value).getName());
		}
		setValueImpl(value);
	}

	public boolean hasValue(){
		return getClassIdentifier() != null && (getIdentifier() != null || getValue()!=null);
	}
	protected Class<?> getImplementationClass(){
		if(getClassIdentifier() == null){
			return null;
		}else{
			try {
				return Class.forName(getClassIdentifier());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
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
		this.value=value;
	}
}
