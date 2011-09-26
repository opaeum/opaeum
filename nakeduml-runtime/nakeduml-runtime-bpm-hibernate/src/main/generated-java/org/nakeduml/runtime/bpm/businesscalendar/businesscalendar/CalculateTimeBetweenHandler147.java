package org.nakeduml.runtime.bpm.businesscalendar.businesscalendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.bpm.businesscalendar.BusinessCalendar;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.ICallEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class CalculateTimeBetweenHandler147 implements ICallEventHandler {
	private Double result;
	private boolean isEvent;
	private Date toDateTime;
	private Date firstOccurrenceScheduledFor;
	private BusinessTimeUnit businessTimeUnit;
	private Date fromDateTIme;

	/** Default constructor for CalculateTimeBetweenHandler147
	 */
	public CalculateTimeBetweenHandler147() {
	}
	
	/** Constructor for CalculateTimeBetweenHandler147
	 * 
	 * @param fromDateTIme 
	 * @param toDateTime 
	 * @param businessTimeUnit 
	 * @param result 
	 * @param isEvent 
	 */
	public CalculateTimeBetweenHandler147(Date fromDateTIme, Date toDateTime, BusinessTimeUnit businessTimeUnit, Double result, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setFromDateTIme(fromDateTIme);
		setToDateTime(toDateTime);
		setBusinessTimeUnit(businessTimeUnit);
		setResult(result);
		this.isEvent=isEvent;
	}

	public BusinessTimeUnit getBusinessTimeUnit() {
		return this.businessTimeUnit;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public Date getFromDateTIme() {
		return this.fromDateTIme;
	}
	
	public String getHandlerUuid() {
		return "252060@_mOhZgNcEEeCJ0dmaHEVVnw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::businesscalendar::BusinessCalendar::calculateTimeBetween";
	}
	
	public Double getResult() {
		return this.result;
	}
	
	public Date getToDateTime() {
		return this.toDateTime;
	}
	
	public boolean handleOn(Object t) {
		BusinessCalendar target = (BusinessCalendar)t;
		if ( isEvent ) {
			return target.consumeCalculateTimeBetweenOccurrence(getFromDateTIme(),getToDateTime(),getBusinessTimeUnit(),getResult());
		} else {
			target.calculateTimeBetween(getFromDateTIme(),getToDateTime(),getBusinessTimeUnit(),getResult());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(151, Value.valueOf(this.getFromDateTIme())));
		result.add(new PropertyValue(150, Value.valueOf(this.getToDateTime())));
		result.add(new PropertyValue(148, Value.valueOf(this.getBusinessTimeUnit())));
		result.add(new PropertyValue(149, Value.valueOf(this.getResult())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setBusinessTimeUnit(BusinessTimeUnit businessTimeUnit) {
		this.businessTimeUnit=businessTimeUnit;
	}
	
	public void setFromDateTIme(Date fromDateTIme) {
		this.fromDateTIme=fromDateTIme;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setResult(Double result) {
		this.result=result;
	}
	
	public void setToDateTime(Date toDateTime) {
		this.toDateTime=toDateTime;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 149:
					this.setResult((Double)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 148:
					this.setBusinessTimeUnit((BusinessTimeUnit)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 150:
					this.setToDateTime((Date)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 151:
					this.setFromDateTIme((Date)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}