package org.nakeduml.runtime.bpm.util;

import java.util.Date;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class OpiumLibraryForBPMFormatter extends AbstractFormatter implements IOpiumLibraryForBPMFormatter {
	static final private ThreadLocal<OpiumLibraryForBPMFormatter> INSTANCE = new ThreadLocal<OpiumLibraryForBPMFormatter>();


	static public OpiumLibraryForBPMFormatter getInstance() {
		OpiumLibraryForBPMFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OpiumLibraryForBPMFormatter());
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