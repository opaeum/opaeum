package org.opeum.runtime.bpm.util;

import java.util.Date;

import org.opeum.runtime.domain.AbstractFormatter;

public class OpeumLibraryForBPMFormatter extends AbstractFormatter implements IOpeumLibraryForBPMFormatter {
	static final private ThreadLocal<OpeumLibraryForBPMFormatter> INSTANCE = new ThreadLocal<OpeumLibraryForBPMFormatter>();


	static public OpeumLibraryForBPMFormatter getInstance() {
		OpeumLibraryForBPMFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OpeumLibraryForBPMFormatter());
		}
		return result;
	}


	@Override
	public String formatDate(Date value){
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String formatDayOfMonth(Integer value){
		return formatInteger(value);
	}


	@Override
	public String formatHourOfDay(Integer value){
		return formatInteger(value);
	}


	@Override
	public String formatMinuteOfHour(Integer value){
		return formatInteger(value);
	}


	@Override
	public Date parseDate(String value){
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer parseDayOfMonth(String value){
		return parseInteger(value);
	}


	@Override
	public Integer parseHourOfDay(String value){
		return parseInteger(value);
	}


	@Override
	public Integer parseMinuteOfHour(String value){
		return parseInteger(value);
	}

}