package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;

import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable()
public abstract class AbstractAnyValue implements IAnyValue{
	public AbstractAnyValue(){
	}
	public AbstractAnyValue(IPersistentObject pi){
		setValue(pi);
	}
	protected abstract String getClassIdentifier(Class<?> c,JavaMetaInfoMap p);
	protected abstract Class<?> getClass(String classUuid,JavaMetaInfoMap p);
	public abstract void setIdentifier(Long identifier);
	public abstract Long getIdentifier();
	public abstract IPersistentObject getValue();
	public abstract void setValue(IPersistentObject v);
	public abstract String getClassIdentifier();
	public abstract void setClassIdentifier(String classIdentifier);
	public void updateBeforeFlush(AbstractPersistence hp){
		if(shouldCascade() && getValue().getId()==null){
			((InternalHibernatePersistence)hp).persistBeforeFlush(getValue());
		}
		setClassIdentifier(getClassIdentifier(IntrospectionUtil.getOriginalClass(getValue()),hp.getMetaInfoMap()));
		setIdentifier(getValue().getId());
	}

	public IPersistentObject getValue(AbstractPersistence p){
		if(hasValue() && (getValue() == null)){
			IPersistentObject reference = retrieveValue(p);
			setValue(reference);
		}
		return getValue();
	}
	@Override
	public IPersistentObject retrieveValue(AbstractPersistence p){
		Class<?> implementationClass = getImplementationClass(p.getMetaInfoMap());
		IPersistentObject reference = (IPersistentObject) p.getReference(implementationClass, getIdentifier());
		return reference;
	}
	public boolean hasValue(){
		return getIdentifier() != null || getValue() != null;
	}
	protected Class<?> getImplementationClass(JavaMetaInfoMap en){
		if(getClassIdentifier() == null){
			return null;
		}else{
			return getClass(getClassIdentifier(), en);
		}
	}


	protected boolean shouldCascade(){
		return false;
	}
}
