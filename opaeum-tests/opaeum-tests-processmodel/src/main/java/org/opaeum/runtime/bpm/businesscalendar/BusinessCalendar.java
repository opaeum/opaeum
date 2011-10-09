package org.opaeum.runtime.bpm.businesscalendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.runtime.bpm.BusinessComponent;
import org.opeum.annotation.NumlMetaInfo;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@MappedSuperclass
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",uniqueConstraints=@UniqueConstraint(columnNames={"business_component","deleted_on"}),name="business_calendar")
@NumlMetaInfo(uuid="252060@_x9fmQNb9EeCJ0dmaHEVVnw")
@AccessType("field")
public class BusinessCalendar extends BusinessCalendarGenerated {
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessCalendar(BusinessComponent owningObject) {
	}
	
	/** Default constructor for BusinessCalendar
	 */
	public BusinessCalendar() {
	}

}