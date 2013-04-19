package org.opaeum.runtime.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class DateTimeStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class MyFromStringStrategy implements FromStringConverter{
		private ThreadLocal<SimpleDateFormat> format=new ThreadLocal<SimpleDateFormat>();
		@Override
		public Object fromString(String val) throws ParseException {
			if(format.get()==null){
				format.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}
			return format.get().parseObject(val);
		}
	}
	public DateTimeStrategyFactory() {
		super("",Date.class,MyFromStringStrategy.class);
	}
}
