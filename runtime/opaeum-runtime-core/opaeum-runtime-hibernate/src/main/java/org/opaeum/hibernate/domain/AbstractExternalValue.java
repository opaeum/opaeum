package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

@AccessType("field")
@Embeddable()
public abstract class AbstractExternalValue extends AbstractAnyValue{
	public abstract String getApplicationIdentifier();
	public abstract void setApplicationIdentifier(String v);
	public void updateBeforeFlush(InternalHibernatePersistence hp){
		Class<? extends IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(getValue());
		if(getValue().getId()==null){
			//Since the order of flush can't be controlled, assume cascading
			hp.persist(getApplicationIdentifier(originalClass), getValue());
		}
		setClassIdentifier(getClassIdentifier(originalClass,hp.getMetaInfoMap()));
		setIdentifier(getValue().getId());
	}

	public abstract String getApplicationIdentifier(Class<? extends IPersistentObject> value);
	public IPersistentObject getValue(InternalHibernatePersistence p){
		if(hasValue() && (getValue() == null)){
			JavaMetaInfoMap mim = p.getMetaInfoMap(getApplicationIdentifier());
			if(mim!=null){
				Class<?> implementationClass = getImplementationClass(mim);
				setValue((IPersistentObject) p.getReference(getApplicationIdentifier(), implementationClass, getIdentifier()));
			}else{
				//TODO think this through,maybe return a proxy that has a method: isProxy()
			}
		}
		return getValue();
	}
}
