package org.opaeum.audit;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.spi.EventSource;

@Entity
@DiscriminatorValue("D")
public class DateTimePropertyChange extends PropertyChange<Date> {
	{
		super.propertyChangeType = 'D';
	}
	private static final long serialVersionUID = 7539902034341057605L;

	public DateTimePropertyChange(String name, Date oldValue, Date newValue) {
		super(name, oldValue, newValue);
	}

	public DateTimePropertyChange() {
		super();
	}

	@Override
	protected String toString(Date t) {
		return new Timestamp(t.getTime()).toString();
	}

	@Override
	protected Timestamp resolveFromString(EventSource em, String stringValue) {
		return Timestamp.valueOf(stringValue);
	}
}
