package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;

import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable()
public abstract class AbstractAnyValue implements IAnyValue {
	public AbstractAnyValue() {
	}

	public AbstractAnyValue(IPersistentObject pi) {
		setValueInternal(pi);
	}

	protected abstract String getClassIdentifier(Class<?> valueClass,
			JavaMetaInfoMap p);

	protected abstract Class<?> getClass(String classUuid, JavaMetaInfoMap p);

	public abstract void setIdentifier(Long identifier);

	public abstract Long getIdentifier();

	public abstract IPersistentObject getValue();

	public abstract void setValueInternal(IPersistentObject v);

	public abstract String getClassIdentifier();

	public abstract void setClassIdentifier(String classIdentifier);

	public void setValue(IPersistentObject v) {
		setIdentifier(null);
		setClassIdentifier(null);
		setValueInternal(v);
	}

	public void updateBeforeFlush(AbstractPersistence hp,
			Class<?> interfaceClass) {
		if (shouldCascade() && getValue().getId() == null) {
			((InternalHibernatePersistence) hp).persistBeforeFlush(getValue());
		}
		Class<?> directlyImplementingClass = getDirectlyImplementingClass(
				interfaceClass, getValue().getClass());
		setClassIdentifier(getClassIdentifier(directlyImplementingClass,
				hp.getMetaInfoMap()));
		setIdentifier(getValue().getId());
	}

	private Class<?> getDirectlyImplementingClass(Class<?> interfaceClass,
			Class<?> implementation) {
		if (!isDirectImplementationOf(implementation, interfaceClass)
				&& implementation.getSuperclass() != Object.class) {
			return getDirectlyImplementingClass(interfaceClass,
					implementation.getSuperclass());
		} else {
			return implementation;
		}
	}

	private boolean isDirectImplementationOf(Class<?> implementation,
			Class<?> interfaceClass) {
		boolean isDirectImplementation = false;
		for (Class<?> class1 : implementation.getInterfaces()) {
			if (class1 == interfaceClass) {
				isDirectImplementation = true;
				break;
			}
		}
		return isDirectImplementation;
	}

	public IPersistentObject getValue(AbstractPersistence p) {
		if (hasValue() && (getValue() == null)) {
			IPersistentObject reference = retrieveValue(p);
			setValueInternal(reference);
		}
		return getValue();
	}

	@Override
	public IPersistentObject retrieveValue(AbstractPersistence p) {
		Class<?> implementationClass = getImplementationClass(p
				.getMetaInfoMap());
		IPersistentObject reference = (IPersistentObject) p.getReference(
				implementationClass, getIdentifier());
		return reference;
	}

	public boolean hasValue() {
		return getIdentifier() != null || getValue() != null;
	}

	protected Class<?> getImplementationClass(JavaMetaInfoMap en) {
		if (getClassIdentifier() == null) {
			return null;
		} else {
			return getClass(getClassIdentifier(), en);
		}
	}

	protected boolean shouldCascade() {
		return false;
	}
}
