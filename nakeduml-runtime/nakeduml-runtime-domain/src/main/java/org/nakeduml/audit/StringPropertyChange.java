package org.nakeduml.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.EventSource;

@Entity
@DiscriminatorValue("S")
public class StringPropertyChange extends PropertyChange<String> {
	{
		super.propertyChangeType='S';
	}

	private static final long serialVersionUID = 11410293840192349L;

	public StringPropertyChange(String name, String oldValue, String newValue) {
		super(name,oldValue,newValue);
	}

	public StringPropertyChange() {
		super();
	}

	@Override
	protected String toString(String t) {
		return t;
	}

	@Override
	protected String resolveFromString(EventSource em, String stringValue) {
		return stringValue;
	}

}
