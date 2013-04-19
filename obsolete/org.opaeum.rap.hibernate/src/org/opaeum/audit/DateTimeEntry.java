package org.opaeum.audit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.opaeum.runtime.event.IDateTimeEntry;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "DateTimeEntry")
@javax.persistence.Table(name = "date_time_entry")
public class DateTimeEntry implements Serializable,IDateTimeEntry{
	private static final long serialVersionUID = -5996095627052884699L;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	Date id;
	@Index(name="idx_year")
	int year;
	@Index(name="idx_quarter")
	String quarter;
	@Index(name="idx_month")
	String month;
	@Column(name = "week_of_year")
	@Index(name="idx_week_of_year")
	int weekOfYear;
	@Column(name = "day_of_month")
	@Index(name="idx_day_of_month")
	int dayOfMonth;
	@Index(name = "idx_day_of_week")
	@Column(name = "day_of_week")
	String dayOfWeek;
	@Index(name="idx_year")
	int hour;
	int minute;
	int second;
	public DateTimeEntry(){
	}
	public DateTimeEntry(Date d){
		this.id = d;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		year = cal.get(Calendar.YEAR);
		month = getMonthFormatter().format(d);
		quarter = "Q" + (((cal.get(Calendar.MONTH) + 1) / 4) + 1);
		weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		dayOfWeek = getDayFormatter().format(d);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
	}
	public Date getId(){
		return id;
	}
	private SimpleDateFormat getDayFormatter(){
		return new SimpleDateFormat("EEEEE");
	}
	private SimpleDateFormat getMonthFormatter(){
		return new SimpleDateFormat("MMMMM");
	}
}
