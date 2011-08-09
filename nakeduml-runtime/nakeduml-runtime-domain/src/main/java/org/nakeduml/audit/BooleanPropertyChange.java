package org.nakeduml.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.event.EventSource;

@Entity()
@DiscriminatorValue(value="B")
public class BooleanPropertyChange extends PropertyChange<Boolean> {
	{
		super.propertyChangeType='B';
	}

	private static final long serialVersionUID = 11410293840192349L;
	@Transient
	Boolean value;
	public BooleanPropertyChange(String name, Boolean oldValue, Boolean newValue) {
		super(name,oldValue, newValue);
		value=newValue;
	}

	public BooleanPropertyChange() {
		super();
	}

	@Override
	protected String toString(Boolean t) {
		return t.toString();
	}

	@Override
	protected Boolean resolveFromString(EventSource em, String stringValue) {
		return Boolean.valueOf(stringValue);
	}

}
