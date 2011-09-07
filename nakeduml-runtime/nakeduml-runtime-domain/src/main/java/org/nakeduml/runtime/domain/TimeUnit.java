package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public enum TimeUnit implements Serializable,IEnum{
	YEAR("year"),
	MONTH("month"),
	WEEK("week"),
	DAY("day"),
	HOUR("hour"),
	MINUTE("minute"),
	SECOND("second");
	private String name;
	TimeUnit(String code){
		this.name = code;
	}
	public static TimeUnit lookup(String name){
		for(TimeUnit tu:values()){
			if(tu.getName().equals(name) || tu.name().equals(name)){
				return tu;
			}
		}
		return null;
	}
	public String getName(){
		return this.name;
	}
	public Date addTimeTo(Date from,int number){
		Calendar result = Calendar.getInstance();
		result.setTime(from);
		result.add(getCalendarFieldConstant(), number);
		return result.getTime();
	}
	private int getCalendarFieldConstant(){
		switch(this){
		case YEAR:
			return Calendar.YEAR;
		case MONTH:
			return Calendar.MONTH;
		case WEEK:
			return Calendar.WEEK_OF_YEAR;
		case DAY:
			return Calendar.DAY_OF_YEAR;
		case HOUR:
			return Calendar.HOUR_OF_DAY;
		case MINUTE:
			return Calendar.MINUTE;
		case SECOND:
			return Calendar.SECOND;
		default:
			break;
		}
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getUuid(){
		return null;
	}
}
