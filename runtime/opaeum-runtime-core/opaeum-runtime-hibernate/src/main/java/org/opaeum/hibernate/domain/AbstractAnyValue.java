package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

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
	public void updateBeforeFlush(InternalHibernatePersistence hp){
		if(shouldCascade() && getValue().getId()==null){
			hp.persistBeforeFlush(getValue());
		}
		setClassIdentifier(getClassIdentifier(IntrospectionUtil.getOriginalClass(getValue()),hp.getMetaInfoMap()));
		setIdentifier(getValue().getId());
	}

	public IPersistentObject getValue(InternalHibernatePersistence p){
		if(hasValue() && (getValue() == null)){
			Class<?> implementationClass = getImplementationClass(p.getMetaInfoMap());
			setValue((IPersistentObject) p.getReference(implementationClass, getIdentifier()));
		}
		return getValue();
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
