package org.nakeduml.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.EventSource;

@Entity
@DiscriminatorValue("I")
public class IntegerPropertyChange extends PropertyChange<Long> {
	{
		super.propertyChangeType='I';
	}

	private static final long serialVersionUID = -8614030785868441405L;
	public IntegerPropertyChange() {
		super();
	}

	public IntegerPropertyChange(String name, Number oldValue, Number newValue) {
		super(name,toLong(oldValue),toLong(newValue));
	}

	private static Long toLong(Number oldValue) {
		return oldValue==null?null:oldValue.longValue();
	}


	@Override
	protected Long resolveFromString(EventSource em, String stringValue) {
		return Long.valueOf(stringValue);
	}

	@Override
	protected String toString(Long t) {
		return t.toString();
	}


}
