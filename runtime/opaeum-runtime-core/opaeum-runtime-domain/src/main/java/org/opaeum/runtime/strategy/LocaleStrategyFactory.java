package org.opaeum.runtime.strategy;

import java.text.ParseException;
import java.util.Locale;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class LocaleStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class MyFromStringConverter implements FromStringConverter{

		@Override
		public Object fromString(String val) throws ParseException {
			String[] split = val.split("\\_");
			if(split.length==1){
				return new Locale(split[0]);
			}else if(split.length==2){
				return new Locale(split[0],split[1]);
			}else if(split.length==3){
				return new Locale(split[0],split[1],split[2]);
			}
			return null;
		}
		
	}
	public LocaleStrategyFactory() {
	super("",Locale.class,MyFromStringConverter.class);
	}
}
