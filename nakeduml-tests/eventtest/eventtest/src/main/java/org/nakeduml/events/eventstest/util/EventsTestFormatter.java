package org.nakeduml.events.eventstest.util;

import java.util.Date;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class EventsTestFormatter extends AbstractFormatter implements IEventsTestFormatter {
	static final private ThreadLocal<EventsTestFormatter> INSTANCE = new ThreadLocal<EventsTestFormatter>();


	static public EventsTestFormatter getInstance() {
		EventsTestFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new EventsTestFormatter());
		}
		return result;
	}


	@Override
	public String formatDate(Date value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String formatDayOfMonth(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String formatHourOfDay(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String formatMinuteOfHour(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Date parseDate(String value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer parseDayOfMonth(String value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer parseHourOfDay(String value) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer parseMinuteOfHour(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}