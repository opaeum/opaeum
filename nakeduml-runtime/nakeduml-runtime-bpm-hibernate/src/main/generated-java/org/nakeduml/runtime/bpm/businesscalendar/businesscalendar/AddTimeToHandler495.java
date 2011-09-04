package org.nakeduml.runtime.bpm.businesscalendar.businesscalendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit;
import org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.IEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class AddTimeToHandler495 implements IEventHandler {
	private Date fromDateTime;
	private BusinessTimeUnit timeUnit;
	private Double numberOfUnits;
	private Date result;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for AddTimeToHandler495
	 */
	public AddTimeToHandler495() {
	}
	
	/** Constructor for AddTimeToHandler495
	 * 
	 * @param fromDateTime 
	 * @param timeUnit 
	 * @param numberOfUnits 
	 * @param isEvent 
	 */
	public AddTimeToHandler495(Date fromDateTime, BusinessTimeUnit timeUnit, Double numberOfUnits, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setFromDateTime(fromDateTime);
		setTimeUnit(timeUnit);
		setNumberOfUnits(numberOfUnits);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public Date getFromDateTime() {
		return this.fromDateTime;
	}
	
	public String getHandlerUuid() {
		return "2451910c_292c_4f23_90d3_97453cd2cb92";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public Double getNumberOfUnits() {
		return this.numberOfUnits;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::businesscalendar::BusinessCalendar::addTimeTo";
	}
	
	public Date getResult() {
		return this.result;
	}
	
	public BusinessTimeUnit getTimeUnit() {
		return this.timeUnit;
	}
	
	public boolean handleOn(Object t) {
		BusinessCalendar target = (BusinessCalendar)t;
		if ( isEvent ) {
			return target.consumeAddTimeToOccurrence(getFromDateTime(),getTimeUnit(),getNumberOfUnits());
		} else {
			setResult(target.addTimeTo(getFromDateTime(),getTimeUnit(),getNumberOfUnits()));
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(497, Value.valueOf(this.getFromDateTime())));
		result.add(new PropertyValue(498, Value.valueOf(this.getTimeUnit())));
		result.add(new PropertyValue(496, Value.valueOf(this.getNumberOfUnits())));
		result.add(new PropertyValue(499, Value.valueOf(this.getResult())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setFromDateTime(Date fromDateTime) {
		this.fromDateTime=fromDateTime;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setNumberOfUnits(Double numberOfUnits) {
		this.numberOfUnits=numberOfUnits;
	}
	
	public void setResult(Date result) {
		this.result=result;
	}
	
	public void setTimeUnit(BusinessTimeUnit timeUnit) {
		this.timeUnit=timeUnit;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 499:
					this.setResult((Date)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 496:
					this.setNumberOfUnits((Double)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 498:
					this.setTimeUnit((BusinessTimeUnit)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 497:
					this.setFromDateTime((Date)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}