package org.opaeum.runtime.bpm.util;

import java.util.Date;

public interface IOpaeumLibraryForBPMFormatter {
	public String formatBinaryLargeObject(byte[] value);
	
	public String formatBoolean(Boolean value);
	
	public String formatDate(Date value);
	
	public String formatDateTime(Date value);
	
	public String formatDayOfMonth(Integer value);
	
	public String formatEMailAddress(String value);
	
	public String formatHourOfDay(Integer value);
	
	public String formatInteger(Integer value);
	
	public String formatMinuteOfHour(Integer value);
	
	public String formatPhoneNumber(String value);
	
	public String formatReal(Double value);
	
	public String formatString(String value);
	
	public String formatText(String value);
	
	public String formatUnlimitedNatural(Integer value);
	
	public byte[] parseBinaryLargeObject(String value);
	
	public Boolean parseBoolean(String value);
	
	public Date parseDate(String value);
	
	public Date parseDateTime(String value);
	
	public Integer parseDayOfMonth(String value);
	
	public String parseEMailAddress(String value);
	
	public Integer parseHourOfDay(String value);
	
	public Integer parseInteger(String value);
	
	public Integer parseMinuteOfHour(String value);
	
	public String parsePhoneNumber(String value);
	
	public Double parseReal(String value);
	
	public String parseString(String value);
	
	public String parseText(String value);
	
	public Integer parseUnlimitedNatural(String value);

}