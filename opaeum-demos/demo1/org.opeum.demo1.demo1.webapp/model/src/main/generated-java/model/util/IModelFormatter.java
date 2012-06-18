package model.util;

import java.util.Date;

public interface IModelFormatter {
	public String formatBinaryLargeObject(byte[] value);
	
	public String formatBoolean(Boolean value);
	
	public String formatDate(Date value);
	
	public String formatDateTime(Date value);
	
	public String formatDayOfMonth(Integer value);
	
	public String formatDollar(Double value);
	
	public String formatEMailAddress(String value);
	
	public String formatEuro(Double value);
	
	public String formatHourOfDay(Integer value);
	
	public String formatInteger(Integer value);
	
	public String formatMinuteOfHour(Integer value);
	
	public String formatPhoneNumber(String value);
	
	public String formatRand(Double value);
	
	public String formatReal(Double value);
	
	public String formatSecondOfMinute(Integer value);
	
	public String formatString(String value);
	
	public String formatText(String value);
	
	public String formatUnlimitedNatural(Integer value);
	
	public String formatWeekOfYear(Integer value);
	
	public String formatYear(Integer value);
	
	public byte[] parseBinaryLargeObject(String value);
	
	public Boolean parseBoolean(String value);
	
	public Date parseDate(String value);
	
	public Date parseDateTime(String value);
	
	public Integer parseDayOfMonth(String value);
	
	public Double parseDollar(String value);
	
	public String parseEMailAddress(String value);
	
	public Double parseEuro(String value);
	
	public Integer parseHourOfDay(String value);
	
	public Integer parseInteger(String value);
	
	public Integer parseMinuteOfHour(String value);
	
	public String parsePhoneNumber(String value);
	
	public Double parseRand(String value);
	
	public Double parseReal(String value);
	
	public Integer parseSecondOfMinute(String value);
	
	public String parseString(String value);
	
	public String parseText(String value);
	
	public Integer parseUnlimitedNatural(String value);
	
	public Integer parseWeekOfYear(String value);
	
	public Integer parseYear(String value);

}