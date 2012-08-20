package org.opaeum.runtime.bpm.util;

public interface IOpaeumLibraryForBPMFormatter {
	public String formatDayOfMonth(Integer value);
	
	public String formatEMailAddress(String value);
	
	public String formatHourOfDay(Integer value);
	
	public String formatMinuteOfHour(Integer value);
	
	public String formatPhoneNumber(String value);
	
	public Integer parseDayOfMonth(String value);
	
	public String parseEMailAddress(String value);
	
	public Integer parseHourOfDay(String value);
	
	public Integer parseMinuteOfHour(String value);
	
	public String parsePhoneNumber(String value);

}