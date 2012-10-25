package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable()
public abstract class AbstractInterfaceValue{
	public AbstractInterfaceValue(){
	}
	public AbstractInterfaceValue(IPersistentObject pi){
		setValue(pi);
	}
	public IPersistentObject getValue(AbstractPersistence p){
		if(hasValue() && (getValue() == null)){
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
		return getClassIdentifier() != null && (getIdentifier() != null || getValue() != null);
	}
	private Class<?> getImplementationClass(){
		if(getClassIdentifier() == null){
			return null;
		}else{
			try{
				return Class.forName(getClassIdentifier());
			}catch(ClassNotFoundException e){
				throw new RuntimeException(e);
			}
		}
	}
	public abstract void setIdentifier(Long identifier);
	public abstract  Long getIdentifier();
	protected abstract  IPersistentObject getValue();
	protected abstract  String getClassIdentifier();
	protected abstract void setClassIdentifier(String classIdentifier);
	protected abstract void setValueImpl(IPersistentObject value);
}
