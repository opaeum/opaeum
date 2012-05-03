package org.opaeum.runtime.strategy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class DateTimeStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	static ThreadLocal<DateFormat> format = new ThreadLocal<DateFormat>();
	public static class DateToStringConversionStrategy implements ToStringConverter{
		@Override
		public String toString(Object val){
			if(val == null){
				return "";
			}else{
				return getFormat().format(val);
			}
		}
	}
	private static DateFormat getFormat(){
		if(format.get() == null){
			format.set(DateFormat.getDateTimeInstance());
		}
		return format.get();
	}
	public static class StringToDateConversionStrategy implements FromStringConverter{
		@Override
		public Date fromString(String val) throws ParseException{
			try{
				return getFormat().parse(val);
			}catch(ParseException e){
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(val);
			}
		}
	}
	public DateTimeStrategyFactory(){
		super(Date.class, DateToStringConversionStrategy.class, StringToDateConversionStrategy.class);
	}
}
