package org.opaeum.runtime.costing;

import java.util.Date;

import org.opaeum.runtime.domain.IBusinessCalendar;
import org.opaeum.runtime.domain.IPersistentObject;

public interface ITimedResourceBase extends IPersistentObject{

	IRatePerTimeUnit getRateEffectiveOn(Date date);

	IBusinessCalendar getBusinessCalendarToUse();

	String toXmlReferenceString();
}
