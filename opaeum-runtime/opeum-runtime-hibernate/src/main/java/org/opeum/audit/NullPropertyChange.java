package org.opeum.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.EventSource;

@Entity
@DiscriminatorValue("N")
public class NullPropertyChange extends PropertyChange<String> {
	{
		super.propertyChangeType='N';
	}


	private static final long serialVersionUID = 7994933368589785131L;

	public NullPropertyChange(String name) {
		super(name,null,null);
	}

	public NullPropertyChange() {
		super();
	}


	@Override
	protected String toString(String t) {
		return null;
	}

	@Override
	protected String resolveFromString(EventSource em, String stringValue) {
		return null;
	}

}
