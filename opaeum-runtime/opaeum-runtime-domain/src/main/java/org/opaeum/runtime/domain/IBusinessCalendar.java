package org.opaeum.runtime.domain;

import java.util.Date;

public interface IBusinessCalendar{

	public abstract double calculateDifference(Date from,Date to,BusinessTimeUnit timeUnit);

	public abstract Date addTimeTo(Date fromDate,BusinessTimeUnit timeUnit,double numberOfUnits);

	public abstract Double convertBusinessTime(Double cumulativeQuantity,BusinessTimeUnit timeUnit,BusinessTimeUnit timeUnit2);
}
