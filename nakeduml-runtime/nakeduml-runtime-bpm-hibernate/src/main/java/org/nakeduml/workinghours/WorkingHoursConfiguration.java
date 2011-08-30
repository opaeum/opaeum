package org.nakeduml.workinghours;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.nakeduml.runtime.domain.TimeUnit;

//This class is timezone agnostic. It assumes that all the Calendar objects
// that it is being passed
// are correctly timezoned.
public class WorkingHoursConfiguration extends WorkingHoursConfigurationGEN{
	private static ThreadLocal instance = new ThreadLocal();
	public static WorkingHoursConfiguration getInstance(){
		if(instance.get() == null){
			WorkingHoursConfiguration whc = new WorkingHoursConfiguration();
			instance.set(whc);
			whc.init(null);
			whc.getWeekDay().setStartHours(8);
			whc.getWeekDay().setEndHours(16);
		}
		return (WorkingHoursConfiguration) instance.get();
	}
	public Date addTimeTo(Date fromDate,TimeUnit timeUnit,float numberOfUnits){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal = addTimeTo(cal, timeUnit, numberOfUnits);
		goToStartOfThisDay(cal);
		return cal.getTime();
	}
	// For testing purposes only
	public Calendar addTimeTo(Calendar c,TimeUnit tu,double noOfUnits){
		if(tu.isBusinessTime()){
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
	private void addTo(Calendar c,long workTimeInMinutes){
		int timeOfDayInMinutes = timeOfDayInMinutes(c);
		WorkDay workDayToUse = getWorkDayToUse(c);
		if(workTimeInMinutes >= 0){
			// If after hours in the evening, move to next morning
			if(timeOfDayInMinutes > workDayToUse.getEndOfDayInMinutes()){
				c.add(Calendar.DATE, 1);
				goToStartOfThisDay(c);
			}
			// If after hours in the morning, move to startOfDay in minutes
			if(timeOfDayInMinutes < workDayToUse.getStartOfDayInMinutes()){
				goToStartOfThisDay(c);
			}
			while(workTimeInMinutes > 0){
				workTimeInMinutes = goForwardADayOrLess(c, workTimeInMinutes);
			}
		}else{
			// If after hours in the evening, move to the end of work day
			if(timeOfDayInMinutes > workDayToUse.getEndOfDayInMinutes()){
				goToEndOfThisDay(c);
			}
			// If after hours in the morning, move to end of the previous day
			if(timeOfDayInMinutes < workDayToUse.getStartOfDayInMinutes()){
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
		int remainingMinutesInDay = timeOfDayInMinutes - workDayToUse.getStartOfDayInMinutes();
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
		int remainingMinutesInDay = workDayToUse.getEndOfDayInMinutes() - timeOfDayInMinutes;
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
		if(!getWorkDayToUse(date).isWorkingDay()){
			return false;
		}else{
			return !(isOnceOffHoliday(date) || isRecurringHoliday(date));
		}
	}
	private boolean isRecurringHoliday(Calendar date){
		Iterator iter1 = getRecurringHoliday().iterator();
		while(iter1.hasNext()){
			RecurringHoliday element = (RecurringHoliday) iter1.next();
			int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
			if(element.getDayOfMonth() == dayOfMonth && element.getMonth() == date.get(Calendar.MONTH) + 1){
				return true;
			}
			if(Calendar.MONDAY == date.get(Calendar.DAY_OF_WEEK)){
				if(element.getDayOfMonth() == dayOfMonth - 1 && element.getMonth() == date.get(Calendar.MONTH) + 1){
					return true;
				}
			}
		}
		return false;
	}
	private boolean isOnceOffHoliday(Calendar date){
		Iterator iter = getHoliday().iterator();
		Calendar holiday = Calendar.getInstance();
		while(iter.hasNext()){
			Holiday h = (Holiday) iter.next();
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
	private long noOfUnitsToMinutes(TimeUnit timeUnit,double noOfUnits){
		long timeInMinutes;
		switch(timeUnit){
		case BUSINESS_MINUTE:
		case ACTUAL_MINUTE:
			timeInMinutes = Math.round(noOfUnits);
			break;
		case BUSINESS_HOUR:
		case ACTUAL_HOUR:
			timeInMinutes = Math.round(noOfUnits * 60);
			break;
		case BUSINESS_DAY:
			timeInMinutes = Math.round(noOfUnits * getBusinessHoursPerDay() * 60);
			break;
		case CALENDAR_DAY:
			timeInMinutes = Math.round(noOfUnits * 24 * 60);
			break;
		case BUSINESS_WEEK:
			timeInMinutes = Math.round(noOfUnits * getBusinessHoursPerWeek() * 60);
			break;
		case CALENDAR_WEEK:
			timeInMinutes = Math.round(noOfUnits * 7 * 24 * 60);
			break;
		case BUSINESS_MONTH:
			timeInMinutes = Math.round(noOfUnits * getBusinessDaysPerMonth() * getBusinessHoursPerDay() * 60);
			break;
		case CALENDAR_MONTH:
			throw new IllegalArgumentException();
		case BUSINESS_YEAR:
			timeInMinutes = Math.round(noOfUnits * 12 * getBusinessDaysPerMonth() * getBusinessHoursPerDay() * 60);
			break;
		case CALENDAR_YEAR:
			throw new IllegalArgumentException();
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
		c.set(Calendar.HOUR_OF_DAY, day.getEndHours());
		c.set(Calendar.MINUTE, day.getEndMinutes());
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
	private void goToStartOfThisDay(Calendar c){
		WorkDay day = getWorkDayToUse(c);
		c.set(Calendar.HOUR_OF_DAY, day.getStartHours());
		c.set(Calendar.MINUTE, day.getStartMinutes());
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
	private WorkDay getWorkDay(WorkDayType type){
		Iterator iter = getWorkDay().iterator();
		while(iter.hasNext()){
			WorkDay element = (WorkDay) iter.next();
			if(element.getType().equals(type)){
				return element;
			}
		}
		return null;
	}
	public WorkDay getWeekDay(){
		return getWorkDay(WorkDayType.WEEKDAY);
	}
	public WorkDay getSaturday(){
		return getWorkDay(WorkDayType.SATURDAY);
	}
	public WorkDay getSunday(){
		return getWorkDay(WorkDayType.SUNDAY);
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
		if(workDay.getStartOfDayInMinutes() <= timeOfDayInMinutes(from)){
			long timeLeftOnFirstDay = workDay.getEndOfDayInMinutes() - timeOfDayInMinutes(current);
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
		if(timeOfDayInMinutes(to) >= workDay.getStartOfDayInMinutes()){// last
			// day
			long timeLeftOnLastDay = workDay.getEndOfDayInMinutes() - timeOfDayInMinutes(to);
			if(timeLeftOnLastDay > 0 && workOn(to)){
				workingTime -= timeLeftOnLastDay;
			}
		}
		workingTime = isNegative ? -workingTime : workingTime;
		return workingTime;
	}
	// JDK5@Override
	@Override
	public int getBusinessDaysPerMonth(){
		if(super.getBusinessDaysPerMonth() == 0){
			super.setBusinessDaysPerMonth(21);
		}
		return super.getBusinessDaysPerMonth();
	}
	// JDK5@Override
	@Override
	public int getBusinessHoursPerDay(){
		if(super.getBusinessHoursPerDay() == 0){
			super.setBusinessHoursPerDay(getWorkDay(WorkDayType.WEEKDAY).getMinutesPerDay());
		}
		return super.getBusinessHoursPerDay();
	}
	// JDK5@Override
	@Override
	public int getBusinessHoursPerWeek(){
		if(super.getBusinessHoursPerWeek() == 0){
			super.setBusinessHoursPerWeek(getBusinessHoursPerDay() * 5 + (getWorkDay(WorkDayType.SATURDAY).getMinutesPerDay())
					+ getWorkDay(WorkDayType.SUNDAY).getMinutesPerDay());
		}
		return super.getBusinessHoursPerWeek();
	}
}
