package org.opaeum.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable
public class ReturnInfo extends InterfaceValue {
	// HACK!! duplicated the state as Hibernate does not seem to handle
	// inheritance in embedabbles
	private Long identifier;
	private String classIdentifier;
	@Transient
	private IPersistentObject value;

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public Long getIdentifier() {
		return identifier;
	}
	@Override
	public IToken getValue(AbstractPersistence p) {
		return (IToken) super.getValue(p);
	}
	protected IPersistentObject getValue() {
		return value;
	}

	protected String getClassIdentifier() {
		return classIdentifier;
	}

	protected void setClassIdentifier(String classIdentifier) {
		this.classIdentifier = classIdentifier;
	}

	protected void setValueImpl(IPersistentObject value) {
		this.value = value;
	}

	protected Class<?> getImplementationClass() {
		if (getClassIdentifier() == null) {
			return null;
		} else {
			JavaMetaInfoMap mim = Environment.getInstance().getMetaInfoMap();
			return mim.getTokenClass(getClassIdentifier());
		}
	}
	public void setValue(IPersistentObject value){
		if(value == null){
			setIdentifier(null);
			setClassIdentifier(null);
		}else{
			IToken token = (IToken) value;
			setIdentifier(value.getId());
			setClassIdentifier(token.getClassIdentifier());
		}
		setValueImpl(value);
	}
}
