package org.nakeduml.library.workinghours.test;

import java.util.Calendar;

import org.nakeduml.runtime.domain.TimeUnit;
import org.nakeduml.workinghours.Holiday;
import org.nakeduml.workinghours.WorkDay;
import org.nakeduml.workinghours.WorkingHoursConfiguration;

import junit.framework.TestCase;

public class WorkingHoursTest extends TestCase{
	WorkingHoursConfiguration wh = new WorkingHoursConfiguration();
	public void testNegativeEarlyInTheMorning() throws Exception{
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 24, 7, 0, 0);// Wednesday at 7AM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_HOUR, -10f);// 
		assertEquals(Calendar.MONDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	public Calendar testNegativeWithSaturday(){
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 23, 11, 0, 0);// Tuesday at 11 AM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_DAY, -4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.THURSDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(11, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		return c;
	}
	public Calendar testNegativeWithHoliday(){
		Holiday holiday = new Holiday();
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 22);
		holiday.setDate(c.getTime());
		this.wh.addToHoliday(holiday);
		c.set(2006, Calendar.MAY, 23, 11, 0, 0);// Tuesday at 11 AM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_DAY, -4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.WEDNESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(11, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		return c;
	}
	public void testNegativeLateInTheEvening() throws Exception{
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 23, 21, 0, 0);// Tuesday at 9PM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_HOUR, -10f);// 
		assertEquals(Calendar.MONDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	public void testAddTo() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2004, Calendar.MARCH, 4, 14, 0, 0);// THURSDAY at 14h00
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_HOUR, 6f);// 6 hours
		assertEquals(Calendar.FRIDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
	}
	public void testAddToEarlyInTheMorning() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2006, Calendar.MAY, 22, 5, 0, 0);// Monday at 05h00 - only starts
		// at 9:30
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_HOUR, 10f);// 10 hours
		assertEquals(Calendar.TUESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	public void testAddToLateInTheEvening() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2006, Calendar.MAY, 22, 19, 0, 0);// Monday at 19h00 - ends at
		// 16:30
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_HOUR, 10f);// 10 hours
		assertEquals(Calendar.WEDNESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	public Calendar testAdToWithSaturday(){
		Calendar c = Calendar.getInstance();
		c.set(2004, Calendar.MARCH, 4, 7, 0, 0);// THURSDAY at 7AM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_DAY, 4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.TUESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(9, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
		return c;
	}
	public void testHoliday(){
		Holiday holiday = new Holiday();
		Calendar c = Calendar.getInstance();
		c.set(2005, Calendar.JANUARY, 2);
		holiday.setDate(c.getTime());
		this.wh.addToHoliday(holiday);
		c.set(2004, Calendar.DECEMBER, 31, 7, 0, 0);// FRIDAY at 7AM
		this.wh.addTimeTo(c, TimeUnit.BUSINESS_DAY, 4f);
		// 4 working days (including
		// saturday),Sunday the 2nd is a holiday so monday should also be
		// be excluded
//		assertEquals(Calendar.THURSDAY, c.get(Calendar.DAY_OF_WEEK));
//		assertEquals(9, c.get(Calendar.HOUR_OF_DAY));
//		assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Override
	public void setUp(){
		// Seven hours a day
		// Work on Saturdays
		this.wh.init(null);
		this.wh.setBusinessHoursPerDay(7);
		WorkDay saturday = this.wh.getSaturday();
		saturday.setStartHours(9);
		saturday.setStartMinutes(30);
		saturday.setEndHours(16);
		saturday.setEndMinutes(30);
		WorkDay weekDay = this.wh.getWeekDay();
		weekDay.setStartHours(9);
		weekDay.setStartMinutes(30);
		weekDay.setEndHours(16);
		weekDay.setEndMinutes(30);
	}
}
