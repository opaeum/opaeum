package org.opeum.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.EventSource;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.domain.IntrospectionUtil;

@Entity
@DiscriminatorValue("E")
public class EntityPropertyChange extends PropertyChange<IPersistentObject> {
	{
		super.propertyChangeType='E';
	}

	private static final long serialVersionUID = -7575355046699353517L;

	public EntityPropertyChange() {
		super();
	}

	public EntityPropertyChange(String name, IPersistentObject oldValue,IPersistentObject value) {
		super(name,oldValue,value);
	}
	@Override
	protected String toString(IPersistentObject value) {
		return IntrospectionUtil.getOriginalClass(value).getName() + "~" + value.getId();
	}

	@Override
	protected IPersistentObject resolveFromString(EventSource em, String stringValue) {
		String[] s=stringValue.split("~");
		return  (IPersistentObject)em.load(IntrospectionUtil.getClass(s[0]), new Long(s[1]));
	}


}
