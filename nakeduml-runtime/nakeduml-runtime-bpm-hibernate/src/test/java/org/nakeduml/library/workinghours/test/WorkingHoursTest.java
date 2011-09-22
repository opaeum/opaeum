package org.nakeduml.library.workinghours.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessCalendar;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit;
import org.nakeduml.runtime.bpm.businesscalendar.OnceOffHoliday;
import org.nakeduml.runtime.bpm.businesscalendar.WorkDay;

public class WorkingHoursTest extends TestCase{
	BusinessCalendar wh = new BusinessCalendar();
	@Test
	public void testNegativeEarlyInTheMorning() throws Exception{
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 24, 7, 0, 0);// Wednesday at 7AM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSHOUR, -10f);//
		assertEquals(Calendar.MONDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Test
	public Calendar testNegativeWithSaturday(){
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 23, 11, 0, 0);// Tuesday at 11 AM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSDAY, -4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.THURSDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(11, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		return c;
	}
	@Test
	public Calendar testNegativeWithHoliday(){
		OnceOffHoliday holiday = new OnceOffHoliday();
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 22);
		holiday.setDate(c.getTime());
		this.wh.addToOnceOffHoliday(holiday);
		c.set(2006, Calendar.MAY, 23, 11, 0, 0);// Tuesday at 11 AM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSDAY, -4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.WEDNESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(11, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
		return c;
	}
	@Test
	public void testNegativeLateInTheEvening() throws Exception{
		Calendar c = Calendar.getInstance();
		c.set(2006, Calendar.MAY, 23, 21, 0, 0);// Tuesday at 9PM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSHOUR, -10f);//
		assertEquals(Calendar.MONDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Test
	public void testAddTo() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2004, Calendar.MARCH, 4, 14, 0, 0);// THURSDAY at 14h00
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSHOUR, 6f);// 6 hours
		assertEquals(Calendar.FRIDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.MINUTE));
	}
	@Test
	public void testAddToEarlyInTheMorning() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2006, Calendar.MAY, 22, 5, 0, 0);// Monday at 05h00 - only starts
		// at 9:30
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSHOUR, 10f);// 10 hours
		assertEquals(Calendar.TUESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Test
	public void testAddToLateInTheEvening() throws Exception{
		Calendar c = Calendar.getInstance();
		// TEst rolling to next day
		c.set(2006, Calendar.MAY, 22, 19, 0, 0);// Monday at 19h00 - ends at
		// 16:30
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSHOUR, 10f);// 10 hours
		assertEquals(Calendar.WEDNESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Test
	public Calendar testAdToWithSaturday(){
		Calendar c = Calendar.getInstance();
		c.set(2004, Calendar.MARCH, 4, 7, 0, 0);// THURSDAY at 7AM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSDAY, 4f);// 4 working days
		// (including
		// saturday)
		assertEquals(Calendar.TUESDAY, c.get(Calendar.DAY_OF_WEEK));
		assertEquals(9, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, c.get(Calendar.MINUTE));
		return c;
	}
	@Test
	public void testHoliday(){
		OnceOffHoliday holiday = new OnceOffHoliday();
		Calendar c = Calendar.getInstance();
		c.set(2005, Calendar.JANUARY, 2);
		holiday.setDate(c.getTime());
		this.wh.addToOnceOffHoliday(holiday);
		c.set(2004, Calendar.DECEMBER, 31, 7, 0, 0);// FRIDAY at 7AM
		this.wh.addTimeTo(c, BusinessTimeUnit.BUSINESSDAY, 4f);
		// 4 working days (including
		// saturday),Sunday the 2nd is a holiday so monday should also be
		// be excluded
		// assertEquals(Calendar.THURSDAY, c.get(Calendar.DAY_OF_WEEK));
		// assertEquals(9, c.get(Calendar.HOUR_OF_DAY));
		// assertEquals(30, c.get(Calendar.MINUTE));
	}
	@Before
	public void setUp(){
		// Seven hours a day
		// Work on Saturdays
		this.wh.init(null);
		this.wh.setBusinessHoursPerDay(7d);
		WorkDay saturday = this.wh.getSaturday();
		saturday.getStartTime().setHours(9);
		saturday.getStartTime().setMinutes(30);
		saturday.getEndTime().setHours(16);
		saturday.getEndTime().setMinutes(30);
		WorkDay weekDay = this.wh.getWeekDay();
		weekDay.getStartTime().setHours(9);
		weekDay.getStartTime().setMinutes(30);
		weekDay.getEndTime().setHours(16);
		weekDay.getEndTime().setMinutes(30);
	}
}
