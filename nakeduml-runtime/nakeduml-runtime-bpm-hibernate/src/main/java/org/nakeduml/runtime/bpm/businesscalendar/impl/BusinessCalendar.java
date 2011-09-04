package org.nakeduml.runtime.bpm.businesscalendar.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit;
import org.nakeduml.runtime.bpm.businesscalendar.OnceOffHoliday;
import org.nakeduml.runtime.bpm.businesscalendar.RecurringHoliday;
import org.nakeduml.runtime.bpm.businesscalendar.TimeOfDay;
import org.nakeduml.runtime.bpm.businesscalendar.WorkDay;
import org.nakeduml.runtime.bpm.businesscalendar.WorkDayKind;
import org.nakeduml.runtime.domain.TimeUnit;

//This class is timezone agnostic. It assumes that all the Calendar objects
// that it is being passed
// are correctly timezoned.
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Filter(name = "noDeletedObjects")
@Entity(name = "BusinessCalendar")
@DiscriminatorColumn(name = "type_descriminator",discriminatorType = javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"business_component","deleted_on"
}),name = "business_calendar")
@NumlMetaInfo(qualifiedPersistentName = "businesscalendar.business_calendar",uuid = "65a77c10_1db1_40f2_9bc5_e3306b228731")
@AccessType("field")
public class BusinessCalendar extends org.nakeduml.runtime.bpm.businesscalendar.BusinessCalendar{
	private static final long serialVersionUID = -161618913396793066L;
	private static ThreadLocal<BusinessCalendar> instance = new ThreadLocal<BusinessCalendar>();
	public static BusinessCalendar getInstance(){
		if(instance.get() == null){
			BusinessCalendar whc = new BusinessCalendar();
			instance.set(whc);
			whc.init(null);
			whc.getWeekDay().setStartTime(new TimeOfDay());
			whc.getWeekDay().setEndTime(new TimeOfDay());
		}
		return (BusinessCalendar) instance.get();
	}
	public Date addTimeTo(Date fromDate,BusinessTimeUnit timeUnit,double numberOfUnits){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal = addTimeTo(cal, timeUnit, numberOfUnits);
		goToStartOfThisDay(cal);
		return cal.getTime();
	}
	// For testing purposes only
	public Calendar addTimeTo(Calendar c,BusinessTimeUnit tu,double noOfUnits){
		if(isBusinessTime(tu)){
			addTo(c, noOfUnitsToMinutes(tu, noOfUnits));
		}else if(tu.equals(TimeUnit.CALENDAR_MONTH)){
			c.add(Calendar.MONTH, (int) Math.round(noOfUnits));
		}else if(tu.equals(TimeUnit.CALENDAR_YEAR)){
			c.add(Calendar.YEAR, (int) Math.round(noOfUnits));
		}else{
			c.add(Calendar.MINUTE, (int) noOfUnitsToMinutes(tu, noOfUnits));
		}
		return c;
	}
	protected boolean isBusinessTime(BusinessTimeUnit tu){
		switch(tu){
		case BUSINESSMINUTE:
		case BUSINESSHOUR:
		case BUSINESSDAY:
		case BUSINESSWEEK:
		case BUSINESSMONTH:
			return true;
		default:
			return false;
		}
	}
	private void addTo(Calendar c,long workTimeInMinutes){
		int timeOfDayInMinutes = timeOfDayInMinutes(c);
		WorkDay workDayToUse = getWorkDayToUse(c);
		if(workTimeInMinutes >= 0){
			// If after hours in the evening, move to next morning
			if(timeOfDayInMinutes > workDayToUse.getEndTime().getMinuteOfDay()){
				c.add(Calendar.DATE, 1);
				goToStartOfThisDay(c);
			}
			// If after hours in the morning, move to startOfDay in minutes
			if(timeOfDayInMinutes < workDayToUse.getStartTime().getMinuteOfDay()){
				goToStartOfThisDay(c);
			}
			while(workTimeInMinutes > 0){
				workTimeInMinutes = goForwardADayOrLess(c, workTimeInMinutes);
			}
		}else{
			// If after hours in the evening, move to the end of work day
			if(timeOfDayInMinutes > workDayToUse.getEndTime().getMinuteOfDay()){
				goToEndOfThisDay(c);
			}
			// If after hours in the morning, move to end of the previous day
			if(timeOfDayInMinutes < workDayToUse.getStartTime().getMinuteOfDay()){
				c.add(Calendar.DATE, -1);
				goToEndOfThisDay(c);
			}
			workTimeInMinutes = Math.abs(workTimeInMinutes);
			while(workTimeInMinutes > 0){
				workTimeInMinutes = goBackwardADayOrLess(c, workTimeInMinutes);
			}
		}
	}
	private long goBackwardADayOrLess(Calendar c,long workTimeInMinutes){
		WorkDay workDayToUse = getWorkDayToUse(c);
		int timeOfDayInMinutes = timeOfDayInMinutes(c);
		int remainingMinutesInDay = timeOfDayInMinutes - workDayToUse.getStartTime().getMinuteOfDay();
		if(workTimeInMinutes < remainingMinutesInDay){
			c.add(Calendar.MINUTE, -(int) workTimeInMinutes);
			return 0;
		}else{
			long result = workTimeInMinutes - remainingMinutesInDay;
			goToPreviousWorkingDay(c);
			goToEndOfThisDay(c);
			return result;
		}
	}
	private long goForwardADayOrLess(Calendar c,long workTimeInMinutes){
		WorkDay workDayToUse = getWorkDayToUse(c);
		int timeOfDayInMinutes = timeOfDayInMinutes(c);
		int remainingMinutesInDay = workDayToUse.getEndTime().getMinuteOfDay() - timeOfDayInMinutes;
		if(workTimeInMinutes < remainingMinutesInDay){
			c.add(Calendar.MINUTE, (int) workTimeInMinutes);
			return 0;
		}else{
			long result = workTimeInMinutes - remainingMinutesInDay;
			goToNextWorkingDay(c);
			goToStartOfThisDay(c);
			return result;
		}
	}
	private boolean workOn(Calendar date){
		if(getWorkDayToUse(date).getMinutesPerDay() == 0){
			return false;
		}else{
			return !(isOnceOffHoliday(date) || isRecurringHoliday(date));
		}
	}
	private boolean isRecurringHoliday(Calendar date){
		Set<RecurringHoliday> recurringHoliday = getRecurringHoliday();
		for(RecurringHoliday element:recurringHoliday){
			int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
			if(element.getDay() == dayOfMonth && element.getMonth().ordinal() == date.get(Calendar.MONTH)){
				return true;
			}
			if(Calendar.MONDAY == date.get(Calendar.DAY_OF_WEEK)){
				if(element.getDay() == dayOfMonth - 1 && element.getMonth().ordinal() == date.get(Calendar.MONTH)){
					return true;
				}
			}
		}
		return false;
	}
	private boolean isOnceOffHoliday(Calendar date){
		Calendar holiday = Calendar.getInstance();
		for(OnceOffHoliday h:getOnceOffHoliday()){
			if(h.getDate().equals(date.getTime())){
				return true;
			}
			if(Calendar.MONDAY == date.get(Calendar.DAY_OF_WEEK)){
				holiday.setTime(h.getDate());
				if((holiday.get(Calendar.YEAR) == date.get(Calendar.YEAR)) && (holiday.get(Calendar.DAY_OF_YEAR) + 1 == date.get(Calendar.DAY_OF_YEAR))){
					return true;
				}
			}
		}
		return false;
	}
	private long noOfUnitsToMinutes(BusinessTimeUnit timeUnit,double noOfUnits){
		long timeInMinutes;
		switch(timeUnit){
		case BUSINESSMINUTE:
		case ACTUALMINUTE:
			timeInMinutes = Math.round(noOfUnits);
			break;
		case BUSINESSHOUR:
		case ACTUALHOUR:
			timeInMinutes = Math.round(noOfUnits * 60);
			break;
		case BUSINESSDAY:
			timeInMinutes = Math.round(noOfUnits * getBusinessHoursPerDay() * 60);
			break;
		case CALENDARDAY:
			timeInMinutes = Math.round(noOfUnits * 24 * 60);
			break;
		case BUSINESSWEEK:
			timeInMinutes = Math.round(noOfUnits * getBusinessHoursPerWeek() * 60);
			break;
		case CALENDARWEEK:
			timeInMinutes = Math.round(noOfUnits * 7 * 24 * 60);
			break;
		case BUSINESSMONTH:
			timeInMinutes = Math.round(noOfUnits * getBusinessDaysPerMonth() * getBusinessHoursPerDay() * 60);
			break;
		// case BUSINESSYEAR:
		// timeInMinutes = Math.round(noOfUnits * 12 * getBusinessDaysPerMonth() * getBusinessHoursPerDay() * 60);
		// break;
		default:
			throw new IllegalStateException("TimeUnit " + timeUnit + " not supported");
		}
		return timeInMinutes;
	}
	private double minutesToNoOfUnits(TimeUnit timeUnit,long minutes){
		double timeInTimeUnit;
		switch(timeUnit){
		case BUSINESS_MINUTE:
			timeInTimeUnit = minutes;
			break;
		case BUSINESS_HOUR:
			timeInTimeUnit = minutes / 60d;
			break;
		case BUSINESS_DAY:
			timeInTimeUnit = minutes / (getBusinessHoursPerDay() * 60);
			break;
		case BUSINESS_WEEK:
			timeInTimeUnit = minutes / (getBusinessHoursPerWeek() * 60);
			break;
		case BUSINESS_MONTH:
			timeInTimeUnit = minutes / (getBusinessDaysPerMonth() * getBusinessHoursPerDay() * 60);
			break;
		default:
			throw new IllegalStateException("TimeUnit " + timeUnit + " not supported");
		}
		return timeInTimeUnit;
	}
	private void goToNextWorkingDay(Calendar c){
		c.add(Calendar.DATE, 1);
		while(!workOn(c)){
			c.add(Calendar.DATE, 1);
		}
	}
	private void goToPreviousWorkingDay(Calendar c){
		c.add(Calendar.DATE, -1);
		while(!workOn(c)){
			c.add(Calendar.DATE, -1);
		}
	}
	private void goToEndOfThisDay(Calendar c){
		WorkDay day = getWorkDayToUse(c);
		c.set(Calendar.HOUR_OF_DAY, day.getEndTime().getHours());
		c.set(Calendar.MINUTE, day.getEndTime().getMinutes());
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
	private void goToStartOfThisDay(Calendar c){
		WorkDay day = getWorkDayToUse(c);
		c.set(Calendar.HOUR_OF_DAY, day.getStartTime().getHours());
		c.set(Calendar.MINUTE, day.getStartTime().getMinutes());
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
	private WorkDay getWorkDayToUse(Calendar c){
		WorkDay day = getWeekDay();
		switch(c.get(Calendar.DAY_OF_WEEK)){
		case Calendar.SATURDAY:
			day = getSaturday();
			break;
		case Calendar.SUNDAY:
			day = getSunday();
			break;
		}
		return day;
	}
	/**
	 * @return the amount of minutes since 00:00 on the morning of the day represented by c
	 */
	private int timeOfDayInMinutes(Calendar c){
		return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
	}
	private WorkDay getWorkDay(WorkDayKind type){
		for(WorkDay element:getWorkDay()){
			if(element.getKind().equals(type)){
				return element;
			}
		}
		return null;
	}
	public WorkDay getWeekDay(){
		return getWorkDay(WorkDayKind.WEEKDAY);
	}
	public WorkDay getSaturday(){
		return getWorkDay(WorkDayKind.SATURDAY);
	}
	public WorkDay getSunday(){
		return getWorkDay(WorkDayKind.SUNDAY);
	}
	public double calculateDifference(Date from,Date to,TimeUnit timeUnit){
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(from);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(to);
		return minutesToNoOfUnits(timeUnit, calculateDifferenceInMinutes(fromCal, toCal));
	}
	private long calculateDifferenceInMinutes(Calendar from,Calendar to){
		boolean isNegative = from.after(to);
		long workingTime = 0;
		if(isNegative){// swap from and to around and then invert the final
			// duration
			Calendar temp = from;
			from = to;
			to = temp;
		}
		Calendar current = (Calendar) from.clone();
		WorkDay workDay = getWorkDayToUse(current);
		if(workDay.getStartTime().getMinuteOfDay() <= timeOfDayInMinutes(from)){
			long timeLeftOnFirstDay = workDay.getEndTime().getMinuteOfDay() - timeOfDayInMinutes(current);
			if(timeLeftOnFirstDay > 0 && workOn(from)){
				workingTime += timeLeftOnFirstDay;
			}
			goToNextWorkingDay(current);// because we have already counted the
			// no of millis on the first day
		}
		goToStartOfThisDay(current);
		while(current.getTimeInMillis() <= to.getTimeInMillis()){
			workDay = getWorkDayToUse(current);
			workingTime += workDay.getMinutesPerDay();
			goToNextWorkingDay(current);
		}
		// now current.getTimeInMillis() > to.getTimeInMillis()
		if(timeOfDayInMinutes(to) >= workDay.getStartTime().getMinuteOfDay()){// last
			// day
			long timeLeftOnLastDay = workDay.getEndTime().getMinuteOfDay() - timeOfDayInMinutes(to);
			if(timeLeftOnLastDay > 0 && workOn(to)){
				workingTime -= timeLeftOnLastDay;
			}
		}
		workingTime = isNegative ? -workingTime : workingTime;
		return workingTime;
	}
	@Override
	public Integer getBusinessDaysPerMonth(){
		if(super.getBusinessDaysPerMonth() == 0){
			super.setBusinessDaysPerMonth(21);
		}
		return super.getBusinessDaysPerMonth();
	}
	public Double getBusinessHoursPerDay(){
		if(super.getBusinessHoursPerDay() == 0){
			super.setBusinessHoursPerDay(getWorkDay(WorkDayKind.WEEKDAY).getMinutesPerDay().doubleValue() / 60);
		}
		return super.getBusinessHoursPerDay();
	}
	@Override
	public Double getBusinessHoursPerWeek(){
		if(super.getBusinessHoursPerWeek() == 0){
			super.setBusinessHoursPerWeek(getBusinessHoursPerDay() * 5 + (getWorkDay(WorkDayKind.SATURDAY).getMinutesPerDay())
					+ getWorkDay(WorkDayKind.SUNDAY).getMinutesPerDay());
		}
		return super.getBusinessHoursPerWeek();
	}
}
